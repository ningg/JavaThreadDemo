package top.ningg.java.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by guoning on 17/4/4.
 */
public class TestOfLocalThread {

    private ThreadLocal<Integer> intValue = new ThreadLocal<Integer>();
    private ThreadLocal<String> strValue = new ThreadLocal<String>();

    public void setIntValue(int value) {
        intValue.set(value);
    }

    public Integer getIntValue() {
        return intValue.get();
    }

    public void setStrValue(String value) {
        strValue.set(value);
    }

    public String getStrValue() {
        return strValue.get();
    }

    public static void main(String[] args) {
        final TestOfLocalThread testOfLocalThread = new TestOfLocalThread();
        // define task
        Task taskOne = new Task(testOfLocalThread);
        Task taskTwo = new Task(testOfLocalThread);

        // define Thread
        Thread threadOne = new Thread(taskOne);
        Thread threadTwo = new Thread(taskTwo);

        // run
        threadOne.start();
        threadTwo.start();

        // sleep
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class Task implements Runnable {

    private TestOfLocalThread testOfLocalThread;

    public Task(TestOfLocalThread testOfLocalThread) {
        this.testOfLocalThread = testOfLocalThread;
    }

    public void run() {
        testOfLocalThread.setStrValue("zero");
        testOfLocalThread.setIntValue(0);
        System.out.println(String.format("threadlocal str value is %s, threadLocal int value is %s", testOfLocalThread.getStrValue(),
                testOfLocalThread.getIntValue()));
    }

}
