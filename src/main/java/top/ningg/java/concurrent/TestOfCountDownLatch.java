package top.ningg.java.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guoning on 17/4/6.
 */
public class TestOfCountDownLatch {

    public static void main(String[] args) {
        // 1.定义线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 2.定义 countDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(5);

        // 3.运行子任务
        for (int index = 0; index < 5; index++) {
            executor.execute(new PrintTask(index, countDownLatch));
        }

        // 4.主任务等待
        try {
            countDownLatch.await();
            System.out.println("main finish!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 5.关闭线程池
        executor.shutdown();
}

    static class PrintTask implements Runnable {
        private Integer intValue;
        private CountDownLatch countDownLatch;

        public PrintTask(Integer intValue, CountDownLatch countDownLatch) {
            this.intValue = intValue;
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            System.out.println(String.format("value is %s", this.intValue));
            countDownLatch.countDown();
        }
    }
}
