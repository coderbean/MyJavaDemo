package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangbo54 on 2019-04-19.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Holder h = new Holder((int) (System.currentTimeMillis() / 1000));
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 50; i++) {
            executorService.submit(() -> {
                try {
                    h.assertSanity();
                    latch.countDown();
                    System.out.println("assertSanity");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            executorService.submit(() -> {
                h.setN((int) (System.currentTimeMillis() / 1000));
                latch.countDown();
                System.out.println("setN");
            });
        }
        latch.await();
        System.out.println("DONE");
    }
}
