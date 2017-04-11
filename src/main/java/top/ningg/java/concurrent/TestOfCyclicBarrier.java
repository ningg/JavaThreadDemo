package top.ningg.java.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class TestOfCyclicBarrier {

    public static void main(String[] args) {
        // 说明：
        // 1. 创建 CyclicBarrier 时，可以指定一个 Runnable 的任务；
        // 2. 所有线程都到齐后，先执行这个任务，之后才会继续执行；
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int index = 0; index < 5; index++) {
            Thread newThread = new Thread(new TaskOfCyclicBarrier(cyclicBarrier));
            newThread.start();
        }
    }

}

class TaskOfCyclicBarrier implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public TaskOfCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("等待所有人到齐后，开始开会...");
        try {
            TimeUnit.SECONDS.sleep(3);
            cyclicBarrier.await();
            System.out.println("开始开会...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
