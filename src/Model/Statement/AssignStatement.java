package Model.Statement;

import Controller.AppException;
import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.States.SymTable.SymTable;

public class AssignStatement implements IStatement {
    private final String varName;
    private final IExpression expression;

    public AssignStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgState execute(ProgState state) throws AppException {
        state.getSymTable().setValue(varName, expression.evaluate(state));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(varName, expression);
    }

    @Override
    public String toString() {
        return varName + " = " + expression.toString();
    }
}
