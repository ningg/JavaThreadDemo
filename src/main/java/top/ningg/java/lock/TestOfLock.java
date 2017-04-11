package top.ningg.java.lock;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by guoning on 17/4/6.
 */
public class TestOfLock {

    public static void main(String[] args) {
        // 分析：
        // 1. 锁：用于并发访问共享资源
        SharedResource sharedResource = new SharedResource();

        // 1. 定义线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 2. 执行任务
        for (int index = 0; index < 500; index++) {
            executor.submit(new TaskOfSet(sharedResource, index));
        }
        for (int index = 0; index < 500; index++) {
            // executor.submit(new TaskOfGet(sharedResource));
        }
        executor.shutdown();
    }
}

class TaskOfGet implements Runnable {

    private SharedResource sharedResource;

    public TaskOfGet(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        System.out.println(String.format(" %s get value is %s", Thread.currentThread().getName(), sharedResource.get()));

    }
}

class TaskOfSet implements Runnable {

    private SharedResource sharedResource;

    private Integer value;

    public TaskOfSet(SharedResource sharedResource, int value) {
        this.sharedResource = sharedResource;
        this.value = value;
    }

    @Override
    public void run() {
        sharedResource.add(value);
        System.out.println(String.format(" %s set value is %s", Thread.currentThread().getName(), value));

    }
}

class SharedResource {

    private ReentrantLock reentrantLock = new ReentrantLock();

    // un thread safe
    private Queue<Integer> queue = new PriorityQueue<>();

    // 新增元素
    public void add(int element) {
        reentrantLock.lock();
        try {
            queue.add(element);
        } finally {
            reentrantLock.unlock();
        }
    }

    // 获取元素
    public int get() {
        reentrantLock.lock();
        try {
            return queue.poll();
        } finally {
            reentrantLock.unlock();
        }
    }
}
