package top.ningg.java.queue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by guoning on 15/9/4.
 */
public class Consumer implements Runnable {

    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String msg;
        try {
            while ("Exited".equals(msg = queue.take().getMsg())) {
                Thread.sleep(10);
                System.out.println("Msg consumed: " + msg);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
