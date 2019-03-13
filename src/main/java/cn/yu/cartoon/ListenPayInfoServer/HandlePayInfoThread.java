package cn.yu.cartoon.ListenPayInfoServer;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/3 21:38
 **/
public class HandlePayInfoThread implements Callable {


    Map messageMap;

    public HandlePayInfoThread(Map message) {
        this.messageMap = message;
    }

    @Override
    public Object call() throws Exception {

        System.out.println("开启了一个" + Thread.currentThread().getName() + "，操作" + messageMap);
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + "做完要关闭了");

        return null;
    }
}
