package aynchronized;

import java.util.concurrent.Semaphore;

/***
 * There are two kinds of threads, oxygen and hydrogen.
 * Your goal is to group these threads to form water molecules.
 * There is a barrier where each thread has to wait until a complete molecule can be formed.
 * Hydrogen and oxygen threads will be given a releaseHydrogen and releaseOxygen method respectfully,
 * which will allow them to pass the barrier. These threads should pass the barrier in groups of three,
 * and they must be able to immediately bond with each other to form a water molecule.
 * You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.
 *
 * In other words:
 *
 * If an oxygen thread arrives at the barrier when no hydrogen threads are present,
 * it has to wait for two hydrogen threads.
 * If a hydrogen thread arrives at the barrier when no other threads are present,
 * it has to wait for an oxygen thread and another hydrogen thread.
 * We don’t have to worry about matching the threads up explicitly; that is,
 * the threads do not necessarily know which other threads they are paired up with.
 * The key is just that threads pass the barrier in complete sets; thus,
 * if we examine the sequence of threads that bond and divide them into groups of three,
 * each group should contain one oxygen and two hydrogen threads.
 *
 * Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.
 *
 *
 *
 * Example 1:
 *
 * Input: "HOH"
 * Output: "HHO"
 * Explanation: "HOH" and "OHH" are also valid answers.
 * Example 2:
 *
 * Input: "OOHHHH"
 * Output: "HHOHHO"
 * Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH",
 * "HOHOHH" and "OHHOHH" are also valid answers.
 */
public class H2O {

    private Semaphore h;
    private Semaphore o;
    private Semaphore h2o;
    public H2O() {
        h = new Semaphore(2);
        o = new Semaphore(1);
        h2o = new Semaphore(3);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        // 如果没有获取到锁，说明水已经形成，则释放h
        h2o.acquire();

        // 如果获取到锁，说明水还没有形成，但是已经贡献了一个H了

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        o.acquire();
        // releaseOxygen.run() outputs "H". Do not change or remove this line.
        releaseOxygen.run();

        h2o.acquire();

        // 水还没有形成
        while (h2o.availablePermits() != 0);

        // 水已经形成
        o.release();
        h.release(2);
        h2o.release(3);
        // 如果获取到锁，说明水还没有形成，但是已经贡献了一个O了

    }
}
