package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jacob on 2019-08-05.
 */
public class Main {
    static class RunnableTask implements Runnable {
        Condition condition;
        Condition condition2;
        Lock lock;

        public RunnableTask(Lock lock, Condition condition, Condition condition2) {
            this.lock = lock; // 注意 condition 一定要配合 Lock 使用。
            this.condition = condition;
            this.condition2 = condition2;
        }

        @Override
        public void run() {
            for (; ; ) {
                try {
                    lock.lock();
                    condition2.signal();
                    System.out.println(Thread.currentThread().getName());
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Lock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();

        executorService.submit(new RunnableTask(lock, a, b));
        executorService.submit(new RunnableTask(lock, b, c));
        executorService.submit(new RunnableTask(lock, c, a));
    }
}
