package top.ningg.java.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by guoning on 17/4/6.
 */
public class TestOfBlockingQueue {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>();

        int capacity = 10;

        for (int i = 0; i < capacity; i++){
            blockingQueue.add(i);
        }

        for (Integer single : blockingQueue){
            System.out.println(single);
        }
    }
}
