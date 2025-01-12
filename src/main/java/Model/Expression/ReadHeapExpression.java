package Model.Expression;

import Exceptions.TypeException;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Types.RefType;
import Model.Utils.MyIDictionary;
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

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType type = expression.typeCheck(typeEnv);
        if(type instanceof RefType) {
            return ((RefType) type).getInner();
        } else {
            throw new TypeException("Heap reading: " + expression.toString() + " is not a RefValue");
        }
    }
}
