package top.ningg.java;

/**
 * Created by guoning on 15/9/4.
 */
public class Demo3ThreadInterrupt {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < Integer.MAX_VALUE; index++){
//                    System.out.println();
                }
            }
        }, "thread1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        thread1.interrupt();

        System.out.println(thread1.isInterrupted());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println("test1");
        System.out.println(Thread.interrupted());
        System.out.println("test2");
        System.out.println(Thread.interrupted());
        System.out.println("test3");

    }


}
