package top.ningg.java.queue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by guoning on 15/9/4.
 */
public class Producer implements Runnable {

    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        for (int index = 0; index < 10000 ; index++){
            Message msg = new Message("msg" + index);
            try {
                Thread.sleep(index%3);
                queue.put(msg);
                System.out.println("Msg produced: " + msg.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Message exitMsg = new Message("Exited");

        try {
            queue.put(exitMsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
