package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Types.RefType;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;
import Model.Values.RefValue;

public class NewStatement implements IStatement {
    private final String varName;
    private final IExpression expression;

    public NewStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }


    @Override
    public ProgState execute(ProgState state) throws StatementException {
        IValue value = expression.evaluate(state);
        state.getSymTable().setValue(varName, new RefValue(state.getHeapTable().allocate(value), value.getType()));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NewStatement(varName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return "new(" + varName + ", " + expression.toString() + ")";
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType typeVar = typeEnv.LookUp(varName);
        IType typeExp = expression.typeCheck(typeEnv);

        if (typeVar.equals(new RefType(typeExp))) {
            return typeEnv;
        }
        else {
            throw new TypeException("New Statement: right hand side and left hand side have different types");
        }
    }
}
