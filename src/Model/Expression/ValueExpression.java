package Model.Expression;

import Controller.AppException;
import Model.States.ProgState;
import Model.States.SymTable.SymTable;
import Model.Values.IValue;

public class ValueExpression implements IExpression {
    private IValue value;

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
}
