package PayInfoSocketServiceTest;

import ServerBF.PayInfoSocket;
import cn.yu.cartoon.ListenPayInfoServer.PayInfoSocketServer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/4 19:03
 **/
public class PayInfoSocketServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //PayInfoSocket payInfoSocketServer = new PayInfoSocket();
        PayInfoSocketServer payInfoSocketServer = new PayInfoSocketServer();
        // 使用Callable方式创建线程，需要FutureTask类的支持，用于接收运算结果，可以使用泛型指定返回值的类型
        FutureTask<Integer> result = new FutureTask<>(payInfoSocketServer);

        Thread thread = new Thread(result);
        thread.start();
        System.out.println(result.get());
    }

}
