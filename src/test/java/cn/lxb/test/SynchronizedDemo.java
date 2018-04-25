package cn.lxb.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 54LXB on 2017-08-11.
 */
public class SynchronizedDemo {

    private boolean ready = false;
    private volatile int result = 0;
    private volatile int number = 1;

    public synchronized void write() {
        ready = true;
        number = 2;
    }

    public synchronized void ready() {
        if (ready) {
            result = number * 3;
        }
        System.out.println("result的值为："+result);
        // result的值为：6
        // result的值为：0
    }

    private class ReadWriteThread extends Thread {

        private boolean flag;

        public ReadWriteThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            super.run();
            if (flag) {
                write();
            } else {
                ready();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        demo.new ReadWriteThread(true).start();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        demo.new ReadWriteThread(false).start();
    }

}

class VolatileDemo {
/*
    private volatile int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.number++;
    }

    public static void main(String[] args) {
        final VolatileDemo demo = new VolatileDemo();
        for (int i=0; i<500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setNumber();
                }
            }).start();
        }
        // 如果还有子线程在运行，主线程就让出CPU资源，
        // 直到所有的子线程都运行完毕，主线程才继续往下执行；
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("number = " + demo.getNumber());
    }*/

/*
    private int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            this.number++;
        }
    }

    public static void main(String[] args) {
        final VolatileDemo demo = new VolatileDemo();
        for (int i=0; i<500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setNumber();
                }
            }).start();
        }
        // 如果还有子线程在运行，主线程就让出CPU资源，
        // 直到所有的子线程都运行完毕，主线程才继续往下执行；
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("number = " + demo.getNumber());
    }*/


    private int number = 0;

    private Lock lock = new ReentrantLock();

    public int getNumber() {
        return number;
    }

    public void setNumber() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();  // 加锁
        try {
            this.number++;
        } finally {
            // 解锁时有可能抛出异常，
            // 在finally语句块里面执行解锁操作
            // 为了保证解锁成功
            lock.unlock(); // 解锁
        }
    }

    public static void main(String[] args) {
        final VolatileDemo demo = new VolatileDemo();
        for (int i=0; i<500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setNumber();
                }
            }).start();
        }
        // 如果还有子线程在运行，主线程就让出CPU资源，
        // 直到所有的子线程都运行完毕，主线程才继续往下执行；
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("number = " + demo.getNumber());
    }
}
