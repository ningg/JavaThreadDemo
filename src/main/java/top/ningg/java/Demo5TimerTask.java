package top.ningg.java;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by guoning on 15/9/5.
 */
public class Demo5TimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Task Started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task Ended");
    }

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer("timer");
        Demo5TimerTask timerTask = new Demo5TimerTask();
        timer.schedule(timerTask, 0, 1000);
        //timer.scheduleAtFixedRate(timerTask, 0, 1 * 1000);
        Thread.sleep(6 * 1000);
        // timer.cancel();
        System.out.println("haha");
        // Thread.sleep(10 * 1000);
    }
}
