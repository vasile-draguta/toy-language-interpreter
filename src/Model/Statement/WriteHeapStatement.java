package Model.Statement;

import Exceptions.StatementException;
import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.Values.IValue;
import Model.Values.RefValue;

public class WriteHeapStatement implements IStatement {
    private String varName;
    private IExpression expression;

    public WriteHeapStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgState execute(ProgState state) throws StatementException {
        IValue address = state.getSymTable().getValue(varName);
        IValue value = expression.evaluate(state);

        if(! (address instanceof RefValue)) {
            throw new StatementException("Value is not a RefValue");
        }

        state.getHeapTable().write(((RefValue) address).getAddress(), value);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(varName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return "WriteHeap(" + varName + ", " + expression.toString() + ")";
    }
}
