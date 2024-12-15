package Model.Expression;

import Exceptions.AppException;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Types.IntegerType;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;

public class ValueExpression implements IExpression {
    private final IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(ProgState state) throws AppException {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public IExpression deepCopy() {
        return null;
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) {
        return value.getType();
    }
}
