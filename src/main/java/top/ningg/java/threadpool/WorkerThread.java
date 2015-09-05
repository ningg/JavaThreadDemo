package top.ningg.java.threadpool;

/**
 * Created by guoning on 15/9/5.
 */
public class WorkerThread extends Thread {

    private int indexValue;

    public WorkerThread(int indexValue) {
        this.indexValue = indexValue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Thread Started: " + indexValue);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Thread Ended: " + indexValue);
    }


}
