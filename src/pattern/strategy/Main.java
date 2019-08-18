package pattern.strategy;

/**
 * Created by jacob on 2019-08-18.
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new Add());
        int operate = context.getOperator().operate(1, 2);
        System.out.println(operate);
        Context context1 = new Context(new Minus());
        System.out.println(context1.getOperator().operate(2, 1));
    }
}
