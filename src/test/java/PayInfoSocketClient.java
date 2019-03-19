import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/3 16:22
 **/
public class PayInfoSocketClient {

    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    public void start() throws IOException {
        // 打开socket通道
        SocketChannel sc = SocketChannel.open();
        //设置为非阻塞
        sc.configureBlocking(false);
        //连接服务器地址和端口
        sc.connect(new InetSocketAddress("localhost", 8089));
        //打开选择器
        Selector selector = Selector.open();
        //注册连接服务器socket的动作
        sc.register(selector, SelectionKey.OP_CONNECT);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            //选择一组键，其相应的通道已为 I/O 操作准备就绪。
            //此方法执行处于阻塞模式的选择操作。
            selector.select();
            //返回此选择器的已选择键集。
            Set<SelectionKey> keys = selector.selectedKeys();
            System.out.println("keys=" + keys.size());
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                // 判断此通道上是否正在进行连接操作。
                if (key.isConnectable()) {
                    sc.finishConnect();
                    sc.register(selector, SelectionKey.OP_WRITE);
                    System.out.println("ListenPayInfoServer connected...");
                    break;
                } else if (key.isWritable()) { //写数据
                    Map map = new HashMap();
                    map.put("payFunds", "9.99");
                    map.put("payTrackNumber", "10135484513545483135484548");
                    ObjectMapper mapper = new ObjectMapper();
                    String sendMessage = mapper.writeValueAsString(map);
                    System.out.println(sendMessage);
                    //ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                    writeBuffer.clear();
                    writeBuffer.put(sendMessage.getBytes());
                    //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
                    writeBuffer.flip();
                    sc.write(writeBuffer);

                    //注册写操作,每个chanel只能注册一个操作，最后注册的一个生效
                    //如果你对不止一种事件感兴趣，那么可以用“位或”操作符将常量连接起来
                    //int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                    //使用interest集合
                    sc.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()){//读取数据
                    System.out.print("receive message:");
                    SocketChannel client = (SocketChannel) key.channel();
                    //将缓冲区清空以备下次读取
                    readBuffer.clear();
                    int num = client.read(readBuffer);
                    System.out.println(new String(readBuffer.array(),0, num));
                    //注册读操作，下一次读取
                    sc.register(selector, SelectionKey.OP_WRITE);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PayInfoSocketClient().start();
    }
}
