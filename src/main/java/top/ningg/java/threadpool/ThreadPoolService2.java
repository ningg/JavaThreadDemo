package top.ningg.java.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by guoning on 15/9/5.
 */
public class ThreadPoolService2 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30 * 1000);
        // ExecutorService executor = new ThreadPoolExecutor(0, 4, 2,
        // TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
        ExecutorService executor = new ThreadPoolExecutor(0, 50, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int index = 0; index < 10; index++) {
            WorkerThread thread = new WorkerThread(index);
            executor.submit(thread);
        }

        Thread.sleep(15 * 1000);
        Thread.sleep(15 * 1000);

        for (int index = 0; index < 3; index++) {
            WorkerThread thread = new WorkerThread(index);
            executor.submit(thread);
        }

        Thread.sleep(15 * 1000);

        Thread.sleep(15 * 1000);
        Thread.sleep(15 * 1000);
        executor.shutdown();
        while (!executor.isShutdown()) {

        }
        System.out.println("Executor Ended");
    }
}
