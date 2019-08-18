package concurrent.singleton;

import sun.jvm.hotspot.utilities.Assert;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhangbo54 on 2019-06-03.
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable task = new Callable<Resource>() {
            @Override
            public Resource call() throws Exception {
                return UnsafeLazyInitialization.getSingleton();
            }
        };
        Future<Resource> submit = executorService.submit(task);
        Future<Resource> submit1 = executorService.submit(task);


        System.out.println(submit.get().hashCode() == submit1.get().hashCode());
        System.out.println(submit.get().toString());
        System.out.println(submit1.get().toString());
        // Assert.that(,"not equal");
        executorService.shutdown();
    }
}
