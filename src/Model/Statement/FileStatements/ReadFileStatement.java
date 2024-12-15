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
import Model.Values.IntegerValue;
import Model.Values.StringValue;

public class ReadFileStatement implements IStatement {
    private final IExpression expression;
    private final String name;

    public ReadFileStatement(IExpression expression, String name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public String toString() {
        return "readFile(" + expression.toString() + ")";
    }

    @Override
    public ProgState execute(ProgState state) {
        IValue value = expression.evaluate(state);

        if(!(value.getType() instanceof StringType)) {
            throw new RuntimeException("File name must be a string");
        }

        IValue readValue = new IntegerValue(state.getFileTable().readFile(((StringValue) value).getValue()));
        state.getSymTable().declareVar(name, readValue);

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(expression.deepCopy(), name);
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        if(!(new StringType().equals(expression.typeCheck(typeEnv)))) {
            throw new TypeException("File name must be a string");
        }
        return typeEnv;
    }
}
