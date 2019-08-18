package reference.soft;

import reference.MemConsumer;
import reference.MyDate;

import java.lang.ref.SoftReference;

/**
 * Created by zhangbo54 on 2019-05-13.
 */
public class Main {
    public static void main(String[] args) {
        SoftReference<MyDate> softReference = new SoftReference<>(new MyDate());
        System.out.println(softReference.get());
        MemConsumer.cosumeMem();
        System.out.println(softReference.get());
    }
}