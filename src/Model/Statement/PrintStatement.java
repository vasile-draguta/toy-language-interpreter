package Model.Statement;

import Model.Expression.IExpression;
import Model.States.ProgState;

public class PrintStatement implements IStatement {
    private final IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    @Override
    public ProgState execute(ProgState state) {
        state.getOutput().addOutput(expression.evaluate(state).toString());
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }
}
