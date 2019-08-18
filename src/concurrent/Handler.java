package concurrent;

/**
 * Created by zhangbo54 on 2019-05-19.
 */
public class Handler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("hahahah");
    }
}
