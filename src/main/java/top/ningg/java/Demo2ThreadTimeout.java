package top.ningg.java;

/**
 * Created by guoning on 15/9/4.
 */
public class Demo2ThreadTimeout {

    public static void main(String[] args) {
        Thread subThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10; index++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this + ": " + index);
                }
            }
        }, "subThread1");

        Thread subThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10; index++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this + ": " + index);
                }
            }
        }, "subThread2");


        subThread1.start();
        subThread2.start();

        try {
            subThread1.join(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            subThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int index = 0 ; index < 10; index++){
            System.out.println("main " + ": " + index);
        }


    }
}
