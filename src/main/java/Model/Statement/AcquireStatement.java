package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Types.IntegerType;
import Model.Utils.MyIDictionary;
import Model.Utils.Pair;
import Model.Values.IValue;
import Model.Values.IntegerValue;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements IStatement {
    private final String var;
    private final Lock lock = new ReentrantLock();

    public AcquireStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgState execute(ProgState state) throws StatementException {
        IValue value = state.getSymTable().getValue(var);

        if(value == null) {
            throw new StatementException("This semaphore does not exist");
        }

        if(!(value.getType().equals(new IntegerType()))) {
            throw new StatementException("Semaphore value is not of type Integer");
        }

        Integer index = ((IntegerValue) value).getValue();
        Pair<Integer, List<Integer>> pair = state.getSemaphoreTable().getSemaphoreValue(index);
        Integer num = pair.getKey();
        List<Integer> list = pair.getValue();
        Integer listSize = list.size();

        lock.lock();
        if(num > listSize) {
            if(!(list.contains(state.getId()))) {
                list.add(state.getId());
                state.getSemaphoreTable().setSemaphore(index, new Pair<>(num, list));
            }
        }
        else {
            state.getExecutionStack().push(this);
        }
        lock.unlock();

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AcquireStatement(var);
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType varType = typeEnv.LookUp(var);

        if(!(varType.equals(new IntegerType()))) {
            throw new TypeException("Acquire variable is not of type Integer");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "acquire("+ var+")";
    }
}
