package bigdecimal;

import java.math.BigDecimal;

/**
 * Created by zhangbo54 on 2019-04-11.
 */
public class Main {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("0.1");
        System.out.println(bigDecimal);
        System.out.println(0.10000000000000000212D); // 输出0.1
        BigDecimal bigDecimal1 = new BigDecimal(0.10000000000000000212D);
        System.out.println(bigDecimal1.doubleValue());
        System.out.println(bigDecimal1);
        System.out.println(0.1D + 0.2D == 0.3D);

        ThreadLocal<Integer> s = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                System.out.println("ssss");
                return 1;
            }
        };
        System.out.println("-------");
        System.out.println(s.get());
    }
}
