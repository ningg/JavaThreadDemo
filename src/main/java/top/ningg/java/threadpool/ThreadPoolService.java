package top.ningg.java.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guoning on 15/9/5.
 */
public class ThreadPoolService {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int index = 0 ; index < 10 ; index++){
            WorkerThread workerThread = new WorkerThread(index);
            executor.execute(workerThread);
        }
        executor.shutdown();
        while (!executor.isShutdown()){}
        System.out.println("ExecutorService Shutdown.");
    }
}
