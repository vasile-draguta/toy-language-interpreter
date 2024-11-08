package Model.Statement;

import Exceptions.StatementException;
import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.Values.BooleanValue;
import Model.Values.IValue;

public class IfStatement implements IStatement {
    private IExpression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public String toString() {
        return "if (" + expression.toString() + ") then (" + thenStatement.toString() + ") else (" + elseStatement.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    @Override
    public ProgState execute(ProgState state) throws StatementException {
        IValue value = expression.evaluate(state);

        if (!(value.getType() instanceof Model.Types.BooleanType)) {
            throw new StatementException("If statement requires a boolean expression");
        }

        if(((BooleanValue)value).getValue()) {
            state.getExecutionStack().push(thenStatement);
        }
        else {
            state.getExecutionStack().push(elseStatement);
        }
        return state;
    }
}
