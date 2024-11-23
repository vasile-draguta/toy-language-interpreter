package Model.Statement.FileStatements;

import Model.Expression.IExpression;
import Model.Statement.IStatement;
import Model.States.ProgState;
import Model.Values.IValue;
import Model.Types.StringType;
import Model.Values.StringValue;

public class OpenFileStatement implements IStatement {
    private final IExpression expression;

    public OpenFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "openFile(" + expression.toString() + ")";
    }

    @Override
    public ProgState execute(ProgState state) {
        IValue value = expression.evaluate(state);
        if(!(value.getType() instanceof StringType)) {
            throw new RuntimeException("File name must be a string");
        }
        state.getFileTable().openFile(((StringValue)value).getValue());
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenFileStatement(expression.deepCopy());
    }
}
