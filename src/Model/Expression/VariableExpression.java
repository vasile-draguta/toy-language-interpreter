package Model.Expression;

import Controller.AppException;
import Model.States.SymTable.SymTable;
import Model.Values.IValue;

public class VariableExpression implements IExpression {
    private final String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public IValue evaluate(SymTable symTable) throws AppException {
        return symTable.getValue(variableName);
    }

    @Override
    public String toString() {
        return variableName;
    }
}
