package weakreference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by jacob on 2019-08-07.
 */
public class MyInteger extends WeakReference<Object> {
    int i;

    public int getI() {
        return i;
    }

    public MyInteger(Integer referent, ReferenceQueue<? super Object> q) {
        super(referent, q);
        this.i = referent;
    }
}
