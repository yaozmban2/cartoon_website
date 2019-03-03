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
 * @author Yu
 * @version 1.0
 * @date 2019/3/3 15:02
 **/
public class PayInfoSocketServer implements Callable {

    private Logger logger = LoggerFactory.getLogger(PayInfoSocketServer.class);

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

        ExecutorService threadPool = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100),new HandlePayInfoThreadFactory("线程"), new ThreadPoolExecutor.CallerRunsPolicy());

        while (!Thread.currentThread().isInterrupted()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key : keys) {
                if (!key.isValid()) {
                    continue;
                }
                if (key.isReadable()) {
                    read(key);
                }
            }
        }

        return null;
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

        }
    }
}
