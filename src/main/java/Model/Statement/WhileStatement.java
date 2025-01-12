package Model.Statement;

import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.Types.BooleanType;
import Model.Types.IType;
import Model.Utils.MyIDictionary;
import Model.Values.BooleanValue;
import Model.Values.IValue;
import Model.Values.IntegerValue;

public class WhileStatement implements IStatement {
    private final IStatement statement;
    private final IExpression expression;

    public WhileStatement(IExpression expression, IStatement condition ) {
        this.statement = condition;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") { " + statement.toString() + " }";
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression, statement.deepCopy());
    }

    @Override
    public ProgState execute(ProgState state) {
        IValue value = expression.evaluate(state);
        if(!(value instanceof BooleanValue))
            throw new RuntimeException("Condition is not a boolean value");

        if(((BooleanValue)value).getValue()) {
            state.getExecutionStack().push(this);
            state.getExecutionStack().push(statement);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) {
        IType typeExpression = expression.typeCheck(typeEnv);
        if(typeExpression.equals(new BooleanType()))
            return statement.typeCheck(typeEnv);
        else
            throw new RuntimeException("The condition of WHILE has not the type boolean");
    }
}
