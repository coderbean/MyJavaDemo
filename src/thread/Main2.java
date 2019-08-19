package thread;

import java.util.concurrent.Semaphore;

/**
 * Created by jacob on 2019-08-18.
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[3]; // 0,1,2 分别是 A、B、C 线程
        Semaphore[] semaphores = new Semaphore[3];
        for (int i = 0; i < semaphores.length; i++) {
            semaphores[i] = new Semaphore(1);
            semaphores[i].acquire();
        }
        semaphores[0].release();
        for (int i = 0; i < threads.length; i++) {
            Semaphore semaphore = semaphores[i];
            Semaphore nextSemaphore = i == semaphores.length - 1 ? semaphores[0] : semaphores[i + 1];
            threads[i] = new Thread(() -> {
                try {
                    for (; ; ) {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName());
                        nextSemaphore.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].setName(((char) ('A' + i)) + "-" + i);
            threads[i].start();
        }
    }
}
