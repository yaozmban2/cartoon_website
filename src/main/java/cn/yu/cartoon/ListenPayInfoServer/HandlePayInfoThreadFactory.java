package cn.yu.cartoon.ListenPayInfoServer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/3 18:30
 **/
public class HandlePayInfoThreadFactory implements ThreadFactory {

    private int counter;
    private String name;
    private List<String> stats;

    public HandlePayInfoThreadFactory(String name) {
        counter = 0;
        this.name = name;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable run) {

        Thread thread = new Thread(run, name + "-Thread" + counter);
        counter++;
        stats.add(String.format("Created thread %d with name %s on%s\n" ,thread.getId() ,thread.getName() ,new Date()));
        return thread;
    }

    public String getStas() {
        StringBuilder builder = new StringBuilder();
        for (String stat : stats) {
            builder.append(stat);
        }
        return builder.toString();
    }
}
