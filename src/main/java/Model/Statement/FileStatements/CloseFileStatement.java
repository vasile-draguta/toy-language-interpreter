package Model.Statement.FileStatements;

import Exceptions.TypeException;
import Model.Expression.IExpression;
import Model.Statement.IStatement;
import Model.States.ProgState;
import Model.Types.BooleanType;
import Model.Types.IType;
import Model.Types.StringType;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;
import Model.Values.StringValue;

public class CloseFileStatement implements IStatement {
    private final IExpression expression;

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

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
       if(expression.typeCheck(typeEnv).equals(new StringType())) {
           return typeEnv;
       } else {
           throw new TypeException("Close file statement: expression is not a string");
       }
    }
}
