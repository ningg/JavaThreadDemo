package top.ningg.java.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by guoning on 17/4/7.
 */
public class TestOfHashMap {

    public static void main(String[] args) {
        // 1. 定义线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 2. 提交任务
        // Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (int index = 0; index < 5; index++) {
            executor.submit(new TaskOfMap(map));
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.size());

        // 3.关闭线程池
        executor.shutdown();
    }

}

class TaskOfMap implements Runnable {

    private Map<String, Integer> map;

    public TaskOfMap(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        for (int index = 0; index < 100; index++) {
            String key = String.format("%s [%s]", Thread.currentThread().getName(), index);
            map.put(key, index);
        }
    }
}
