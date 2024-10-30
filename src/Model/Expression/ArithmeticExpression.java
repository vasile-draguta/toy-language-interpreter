package Model.Expression;

import Model.States.ProgState;
import Model.Values.IValue;

public class ArithmeticExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final String operator;

    public ArithmeticExpression(String operator, IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(ProgState state) {
        return left.evaluate(state).compose(right.evaluate(state), operator);
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(operator, left.deepCopy(), right.deepCopy());
    }
}
