package reference;

import java.util.Date;

/**
 * Created by zhangbo54 on 2019-05-13.
 */
public class MyDate extends Date {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("obj [Date: " + this.getTime() + "] is gc");
    }

    @Override
    public String toString() {
        return "Date: " + this.getTime();
    }
}
