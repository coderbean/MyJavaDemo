package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbo54 on 2019-05-19.
 */
public class UnCaught {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("tread");
            throw new RuntimeException("exception");
        });
        thread.setUncaughtExceptionHandler(new Handler());


        // ExecutorService executorService = Executors.newFixedThreadPool(1);
        // executorService.execute(thread);
        // executorService.shutdown();
        // while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        //     ;
        // }

        thread.start();

    }
}
