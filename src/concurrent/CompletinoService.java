package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by zhangbo54 on 2019-04-28.
 */
public class CompletinoService {
    BlockingQueue<QueueingFuture> queueingFutures;
    ExecutorService executorService;


    <T> Future<T> submit(Callable<T> task) {
        return (Future<T>) executorService.submit(new QueueingFuture<>(task));
    }

    Future<?> submit(Runnable task) {
        return executorService.submit(new QueueingFuture(task, null));
    }

    private class QueueingFuture<V> extends FutureTask<V> {
        public QueueingFuture(Callable<V> callable) {
            super(callable);
        }

        public QueueingFuture(Runnable runnable, V result) {
            super(runnable, result);
        }

        @Override
        protected void done() {
            queueingFutures.add(this);
        }
    }

}


