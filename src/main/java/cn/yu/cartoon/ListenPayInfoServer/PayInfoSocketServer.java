package cn.yu.cartoon.ListenPayInfoServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 获取支付通知的NIO服务端
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/3 15:02
 **/
public class PayInfoSocketServer implements Callable {

    private Logger logger = LoggerFactory.getLogger(PayInfoSocketServer.class);

    ExecutorService threadPool = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(100),new HandlePayInfoThreadFactory("监听支付宝通知线程"), new ThreadPoolExecutor.CallerRunsPolicy());

    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    String str;

    @Override
    public Object call() throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8089));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("服务端连接打开了!");

        while (!Thread.currentThread().isInterrupted()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    read(key);
                }
                keyIterator.remove();
            }
        }

        return 1;
    }

    /**
     * 通过此方法给通道注册一个读操作
     *
     * @author Yu
     * @date 20:37 2019/3/4
     **/
    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        logger.info("a new client connected "+socketChannel.getRemoteAddress());
    }

    /**
     *  读取缓冲池里的信息
     *      若是支付宝付款信息的通知 就开启一个线程来处理 数据入库操作
     *
     * @author Yu
     * @date 21:13 2019/3/3
     **/
    private void read(SelectionKey key) throws IOException {

        String payFunds = "payFunds";
        String payTrackNumber = "payTrackNumber";

        SocketChannel socketChannel = (SocketChannel) key.channel();
        this.readBuffer.clear();
        int numRead;
        try {
            numRead = socketChannel.read(this.readBuffer);
        } catch (IOException e) {
            key.cancel();
            socketChannel.close();
            return;
        }
        str = new String(readBuffer.array(), 0, numRead);

        //json字符串转化回HashMap对象
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(str, HashMap.class);
        if (null != map.get(payFunds) && !"".equals(map.get(payFunds))) {
            threadPool.submit(new HandlePayInfoThread(map));
        }
    }
}
