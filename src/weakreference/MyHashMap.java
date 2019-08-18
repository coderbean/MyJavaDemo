package weakreference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jacob on 2019-08-07.
 */
public class MyHashMap {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Map<MyInteger, String> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            Integer integer = i;
            MyInteger myInteger = new MyInteger(integer, referenceQueue);
            // WeakReference<Integer> reference = new WeakReference<>(i, referenceQueue);
            map.put(myInteger, integer.toString());
        }

        map.forEach((k, v) -> {
            if (k.get() == null) {
                System.out.println(k.get() + ":" + v);
            }
        });
        System.out.println(map.size());
        MyInteger i;
        while ((i = (MyInteger) referenceQueue.poll()) != null) {
            // System.out.println(i.getI());
            // System.out.println(i);
            String s = map.remove(i);
        }
        System.out.println(map.size());
        System.out.println("888");
    }
}
