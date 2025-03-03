package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.Expression.IExpression;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Types.IntegerType;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;
import Model.Values.IntegerValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphoreStatement implements IStatement {
    private final String var;
    private final IExpression expression;
    private final Lock lock = new ReentrantLock();


    public CreateSemaphoreStatement(String var, IExpression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgState execute(ProgState state) throws StatementException {
        IValue value = expression.evaluate(state);

        if(value == null) {
            throw new StatementException("Semaphore value evaluated to NULL");
        }

        if(!(value.getType().equals(new IntegerType()))) {
            throw new StatementException("Semaphore value must be an Integer");
        }

        lock.lock();
        Integer num = ((IntegerValue) value).getValue();
        int index = state.getSemaphoreTable().createSemaphore(num);
        state.getSymTable().declareVar(var, new IntegerValue(index));
        lock.unlock();

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CreateSemaphoreStatement(var, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType varType = typeEnv.LookUp(var);
        IType exprType = expression.typeCheck(typeEnv);

        if(!(varType.equals(new IntegerType()))) {
            throw new TypeException("Semaphore variable is not of type Integer");
        }

        if(!(exprType.equals(new IntegerType()))) {
            throw new TypeException("Semaphore Value is not of type Integer");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "CreateSemaphore(" + var + ", " + expression.toString() + ")";
    }
}
