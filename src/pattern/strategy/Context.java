package pattern.strategy;

/**
 * Created by jacob on 2019-08-18.
 */
public class Context {
    private Operator operator;

    public Context(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }
}
