package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Utils.MyIDictionary;

public class AssignStatement implements IStatement {
    private final String varName;
    private final IExpression expression;

    public AssignStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgState execute(ProgState state) throws StatementException {
        state.getSymTable().setValue(varName, expression.evaluate(state));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(varName, expression);
    }

    @Override
    public String toString() {
        return varName + " = " + expression.toString();
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType varType = typeEnv.LookUp(varName);
        IType expType = expression.typeCheck(typeEnv);
        if (!varType.equals(expType))
            throw new TypeException("Assignment: right hand side and left hand side have different types");
        return typeEnv;
    }
}
