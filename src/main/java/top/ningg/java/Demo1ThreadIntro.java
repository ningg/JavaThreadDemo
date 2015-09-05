
package top.ningg.java;

/**
 * 目标:
 * 1. Thread 自带的属性
 * 2. Thread 自带的方法
 */
public class Demo1ThreadIntro {

    public static void main(String[] args) {
        Demo1ThreadIntro demo1ThreadIntro = new Demo1ThreadIntro();
        SubThread1 subThread1 = demo1ThreadIntro.new SubThread1();
        subThread1.start();
        SubThread2 subThread2 = demo1ThreadIntro.new SubThread2();
        new Thread(subThread2).start();
        Thread subThread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
            }
        });
        subThread3.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subThread1.start();
        System.out.println("End");
    }

    class SubThread1 extends Thread{
        @Override
        public void run() {
            System.out.println(this);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class SubThread2 implements Runnable{

        @Override
        public void run() {
            System.out.println(this);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
