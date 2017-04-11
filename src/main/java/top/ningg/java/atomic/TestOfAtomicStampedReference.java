package top.ningg.java.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by guoning on 17/4/9.
 */
public class TestOfAtomicStampedReference {

    // 通用的 AtomicInteger ，使用 volatile + 「循环 CAS」机制，实现「原子自增」
    // 存在的问题：无法解决 ABA 的问题。
    // private static AtomicInteger value = new AtomicInteger(0);
    // 为了解决 CAS 面临的 ABA 问题，需要引入「版本号」机制，不仅取值要想等，版本号也要相等，才能更新。
    private static AtomicStampedReference<Integer> value = new AtomicStampedReference<Integer>(0, 0);

    public static void main(String[] args) throws InterruptedException {
        // 设定初始值
        value.set(0, 0);

        // 定义线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 提交任务
        for (int index = 0; index < 10; index++) {
            executor.submit(new TaskOfAtomicStampedReference(value));
        }

        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) {
                System.out.println(value.getReference());
                break;
            }
        }

    }

}

class TaskOfAtomicStampedReference implements Runnable {

    private AtomicStampedReference<Integer> integer;

    public TaskOfAtomicStampedReference(AtomicStampedReference<Integer> integer) {
        this.integer = integer;
    }

    @Override
    public void run() {
        // 自增 1000 次
        for (int index = 0; index < 10000; index++) {
            boolean result = integer.compareAndSet(integer.getReference(), integer.getReference()+1, integer.getStamp(), integer.getStamp()+1);
            if (!result){
                System.out.println("带版本号的 CAS 失败一次");
            }
        }
    }
}
