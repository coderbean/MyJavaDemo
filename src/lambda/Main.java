package lambda;

import java.util.function.Consumer;

/**
 * Created by jacob on 2019-08-05.
 */
public class Main {
    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        consumer.accept("hahah");
    }
}
