package concurrent.singleton;

import java.util.StringJoiner;

/**
 * Created by zhangbo54 on 2019-06-03.
 */
public class Resource {
    private int i;
    private int j;

    public Resource(int i, int j) {
        this.i = i;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.j = j;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Resource.class.getSimpleName() + "[", "]")
                .add("i=" + i)
                .add("j=" + j)
                .toString();
    }
}
