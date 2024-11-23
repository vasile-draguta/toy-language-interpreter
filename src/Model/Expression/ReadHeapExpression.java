package Model.Expression;

import Model.States.ProgState;
import Model.Values.IValue;
import Model.Values.RefValue;

public class ReadHeapExpression implements IExpression {
    private final IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(ProgState state) {
        IValue value = expression.evaluate(state);

        if(! (value instanceof RefValue)) {
            throw new RuntimeException("Value is not a RefValue");
        }

        return state.getHeapTable().read(((RefValue) value).getAddress());
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "HeapReading(" + expression.toString() + ")";
    }
}
