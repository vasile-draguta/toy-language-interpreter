package Model.Statement;

import Exceptions.StatementException;
import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.Values.IValue;
import Model.Values.RefValue;

public class NewStatement implements IStatement {
    private String varName;
    private IExpression expression;

    public NewStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }


    @Override
    public ProgState execute(ProgState state) throws StatementException {
        IValue value = expression.evaluate(state);
        state.getSymTable().setValue(varName, new RefValue(state.getHeapTable().allocate(value), value.getType()));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NewStatement(varName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return "new(" + varName + ", " + expression.toString() + ")";
    }
}
