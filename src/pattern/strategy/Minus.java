package pattern.strategy;

/**
 * Created by jacob on 2019-08-18.
 */
public class Minus implements Operator {
    @Override
    public int operate(int a, int b) {
        return a - b;
    }
}
