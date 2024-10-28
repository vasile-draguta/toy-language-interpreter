package Model.Expression;

import Controller.AppException;
import Model.States.ProgState;
import Model.States.SymTable.SymTable;
import Model.Values.IValue;

public class VariableExpression implements IExpression {
    private final String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public IValue evaluate(ProgState state) throws AppException {
        return state.getSymTable().getValue(variableName);
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(variableName);
    }
}
