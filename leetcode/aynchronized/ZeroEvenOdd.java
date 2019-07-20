package aynchronized;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;
    private int i = 1;

    private Semaphore s1 = new Semaphore(1);
    private Semaphore s2 = new Semaphore(0);
    private Semaphore s3 = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            s1.acquire();
            if (i > n) {
                break;
            }
            printNumber.accept(0);
            if (i % 2 == 1)
                s2.release();
            else s3.release();
        }
        s2.release();
        s3.release();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        while (true) {
            s2.acquire();
            if (i > n) {
                return;
            }
            printNumber.accept(i);
            i++;
            s1.release();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            s3.acquire();
            if (i > n) {
                return;
            }
            printNumber.accept(i);
            i++;
            s1.release();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        IntConsumer intConsumer = System.out::print;
        Runnable a = () ->
        {
            try {
                zeroEvenOdd.zero(intConsumer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable b = () ->
        {
            try {
                zeroEvenOdd.even(intConsumer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable c = () ->
        {
            try {
                zeroEvenOdd.odd(intConsumer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}