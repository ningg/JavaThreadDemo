package top.ningg.java;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by guoning on 15/9/5.
 */
public class Demo6CallableFuture implements Callable<String> {

    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = new ThreadPoolExecutor(0, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50),
                new ThreadPoolExecutor.CallerRunsPolicy());
        List<Future<String>> futures = Lists.newLinkedList();
        for (int index = 0 ; index <100; index++){
            Callable<String> callable = new Demo6CallableFuture();
            futures.add(executor.submit(callable));
        }
        // Thread.sleep(5 * 1000);

        for (Future future : futures){
            try {
                System.out.println(future.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }
}
