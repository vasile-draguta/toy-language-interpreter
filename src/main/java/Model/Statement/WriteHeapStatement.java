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

public class WriteHeapStatement implements IStatement {
    private final String varName;
    private final IExpression expression;

    public WriteHeapStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgState execute(ProgState state) throws StatementException {
        IValue address = state.getSymTable().getValue(varName);
        IValue value = expression.evaluate(state);

        if(! (address instanceof RefValue)) {
            throw new StatementException("Value is not a RefValue");
        }

        state.getHeapTable().write(((RefValue) address).getAddress(), value);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(varName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return "WriteHeap(" + varName + ", " + expression.toString() + ")";
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType addressType = typeEnv.LookUp(varName);
        IType expressionType = expression.typeCheck(typeEnv);

        if(!(addressType instanceof RefType)) {
            throw new TypeException("Write heap expression does not evaluate to a RefType");
        }
        return typeEnv;
    }
}
