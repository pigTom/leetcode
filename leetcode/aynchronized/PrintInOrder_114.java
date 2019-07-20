package aynchronized;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * Suppose we have a class:
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 *
 * The same instance of Foo will be passed to three different threads.
 * Thread A will call first(), thread B will call second(), and thread C
 * will call three(). Design a mechanism and modify the program to ensure
 * that second() is executed after first(), and third() is executed after second().
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: "firstsecondthird"
 * Explanation: There are three threads being fired asynchronously.
 * The input [1,2,3] means thread A calls first(), thread B calls second(),
 * and thread C calls third().
 *
 * "firstsecondthird" is the correct output.
 *
 *
 * Example 2:
 *
 * Input: [1,3,2]
 * Output: "firstsecondthird"
 * Explanation: The input [1,3,2] means thread A calls first(),
 * thread B calls third(), and thread C calls second().
 * "firstsecondthird" is the correct output.
 */
public class PrintInOrder_114 {


    class Foo {

//        public Foo() {
//
//        }


//        // 先运行
//        private int flag = 0;
//        public void first(Runnable printFirst) throws InterruptedException {
//            // printFirst.run() outputs "first". Do not change or remove this line.
//            synchronized (this) {
//                printFirst.run();
//                flag = 1;
//                this.notify();
//            }
//
//        }
//
//        public void second(Runnable printSecond) throws InterruptedException {
//
//            // printSecond.run() outputs "second". Do not change or remove this line.
//            synchronized (this) {
//                while (flag != 1) {
//                    this.notify();
//                   this.wait();
//                }
//                printSecond.run();
//                flag = 2;
//                this.notify();
//            }
//        }
//
//        public void third(Runnable printThird) throws InterruptedException {
//
//            synchronized (this) {
//                while (flag != 2) {
//                    this.notify();
//                    this.wait();
//                }
//            }
//            // printThird.run() outputs "third". Do not change or remove this line.
//            printThird.run();
//        }


        AtomicInteger count;
        Semaphore run2, run3;

        public Foo() {
            run2 = new Semaphore(0);
            run3 = new Semaphore(0);
        }

//        public Foo() {
//            count = new AtomicInteger(0);
//        }

        public void first(Runnable printFirst) throws InterruptedException {


            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            run2.release();

        }

        public void second(Runnable printSecond) throws InterruptedException {


            // printSecond.run() outputs "second". Do not change or remove this line.
            run2.acquire();
            printSecond.run();
            run3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {

            run3.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    class ThreadA extends Thread {
        private Foo foo;
        private Runnable runnable;

        public void setFooAndRun(Foo foo, Runnable runnable) {
            this.runnable = runnable;
            this.foo = foo;
        }
        @Override
        public void run() {
            try {

                foo.first(runnable);
                synchronized (foo) {
                    foo.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ThreadB extends Thread {
        private Foo foo;
        private Runnable runnable;

        public void setFooAndRun(Foo foo, Runnable runnable) {
            this.runnable = runnable;
            this.foo = foo;
        }
        @Override
        public void run() {
            try {
                foo.second(runnable);
                synchronized (foo) {
                    foo.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ThreadC extends Thread {
        private Foo foo;
        private Runnable runnable;

        public void setFooAndRun(Foo foo, Runnable runnable) {
            this.runnable = runnable;
            this.foo = foo;
        }
        @Override
        public void run() {
            try {
                foo.third(runnable);
                synchronized (foo) {
                    foo.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void test (int[] nums) throws Exception{
        Foo f = new Foo();
        Runnable[] rr = new Runnable[3];
        rr[0] = ()-> System.out.print("first");
        rr[1] = ()-> System.out.print("second");
        rr[2] = ()-> System.out.print("third");
        Thread[] t = new Thread[3];
        Thread a = new ThreadA();
        ((ThreadA) a).setFooAndRun(f, rr[0]);
        Thread b = new ThreadB();
        ((ThreadB) b).setFooAndRun(f, rr[1]);
        Thread c = new ThreadC();
        ((ThreadC) c).setFooAndRun(f, rr[2]);

        t[0] = a;
        t[1] = b;
        t[2] = c;

        for (int i : nums) {
            t[i-1].start();
        }
    }

    public static void main(String[ ] args) throws Exception{
            int[] num = {1,3,2};
       new PrintInOrder_114().test(num);
    }
}
