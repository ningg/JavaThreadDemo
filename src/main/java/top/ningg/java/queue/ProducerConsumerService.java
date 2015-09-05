package top.ningg.java.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by guoning on 15/9/4.
 */
public class ProducerConsumerService {

    public static void main(String[] args) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(20);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
