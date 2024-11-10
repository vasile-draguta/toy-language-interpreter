package Model.Statement.FileStatements;

import Model.Expression.IExpression;
import Model.Statement.IStatement;
import Model.States.ProgState;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;

public class CloseFileStatement implements IStatement {
    private IExpression expression;

    public CloseFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "closeFile(" + expression.toString() + ")";
    }

    @Override
    public ProgState execute(ProgState state) {
        IValue value = expression.evaluate(state);

        if(!(value.getType() instanceof StringType)) {
            throw new RuntimeException("File name must be a string");
        }

        state.getFileTable().closeFile(((StringValue)value).getValue());

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseFileStatement(expression.deepCopy());
    }
}
