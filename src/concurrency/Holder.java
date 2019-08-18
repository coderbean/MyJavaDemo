package concurrency;

/**
 * Created by zhangbo54 on 2019-04-19.
 */
public class Holder {
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("This statement is false.");
        }
    }
}
