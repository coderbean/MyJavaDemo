package forloop;

/**
 * Created by jacob on 2019-08-07.
 */
public class Main {
    public static void main(String[] args) {
        out:
        for (int i = 0; i < 100; i++) {
            System.out.println("i:" + i);
            in:
            for (int j = 0; j < 10; j++) {
                System.out.println("j:" + j);
                if (j == 1) {
                    break out;
                }
            }
        }
    }
}
