import cn.yu.cartoon.ListenPayInfoServer.HandlePayInfoThreadFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/18 21:39
 **/
public class Test {

    public static void main(String[] args) {
        ExecutorService threadpool = null;

            threadpool = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(100),new HandlePayInfoThreadFactory("线程"), new ThreadPoolExecutor.CallerRunsPolicy());

            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            System.out.println("提交一个callable任务到线程池，现在时间是：" + sdf.format(new Date()));

            Future<String> future = threadpool.submit(new CallableTask("呵呵"));

            System.out.println("线程关闭");

    }
}

class CallableTask implements Callable<String> {

    String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(5L);
        System.out.println("这个线程是" + Thread.currentThread().getName());
        System.out.println(name);
        return "success";
    }


}

