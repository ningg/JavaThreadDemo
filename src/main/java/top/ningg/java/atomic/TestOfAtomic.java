package top.ningg.java.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by guoning on 17/4/9.
 */
public class TestOfAtomic {

    // 通用的 AtomicInteger ，使用 volatile + 「循环 CAS」机制，实现「原子自增」
    // 存在的问题：无法解决 ABA 的问题。
    //private static AtomicInteger value = new AtomicInteger(0);
    // 为了解决 CAS 面临的 ABA 问题，需要引入「版本号」机制，不仅取值要想等，版本号也要相等，才能更新。
    private static AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // 设定初始值
        value.set(0);

        // 定义线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 提交任务
        for (int index = 0; index < 1000; index++) {
            executor.submit(new TaskOfAtomic(value));
        }

        executor.shutdown();
        while(true){
            if (executor.isTerminated()) {
                System.out.println(value.get());
                break;
            }
        }

    }

}

class TaskOfAtomic implements Runnable {

    private AtomicInteger integer;

    public TaskOfAtomic(AtomicInteger integer) {
        this.integer = integer;
    }

    @Override
    public void run() {
        // 自增 1000 次
        for (int index = 0; index < 10000; index++) {
            integer.addAndGet(1);
        }
    }
}
