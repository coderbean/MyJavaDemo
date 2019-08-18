package concurrent;

import java.util.concurrent.Semaphore;

/**
 * Created by zhangbo54 on 2019-05-24.
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        semaphore.release();
        semaphore.release();
        semaphore.release();
        semaphore.release();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
    }

}
