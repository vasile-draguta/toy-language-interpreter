package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.Types.BooleanType;
import Model.Types.IType;
import Model.Utils.MyIDictionary;
import Model.Values.BooleanValue;
import Model.Values.IValue;

public class IfStatement implements IStatement {
    private final IExpression expression;
    private final IStatement thenStatement;
    private final IStatement elseStatement;

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
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType expressionType = expression.typeCheck(typeEnv);
        if(expressionType.equals(new BooleanType())) {
            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else {
            throw new TypeException("If statement requires a boolean expression");
        }
    }
}
