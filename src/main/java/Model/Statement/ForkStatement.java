package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.States.ExecutionStack.ExecutionStack;
import Model.States.ProgState;

import Model.Types.IType;
import Model.Utils.MyIDictionary;

public class ForkStatement implements IStatement{
    IStatement innerStatement;

    public ForkStatement(IStatement innerStatement){
        this.innerStatement = innerStatement;
    }

    @Override
    public ProgState execute(ProgState state) throws StatementException {
        return new ProgState(new ExecutionStack(),
                state.getSymTable().copy(),
                state.getOutput(),
                state.getFileTable(),
                state.getHeapTable(),
                innerStatement,
                state.getSemaphoreTable());
    }

    @Override
    public String toString(){
        return "fork(" + innerStatement.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(innerStatement.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        return innerStatement.typeCheck(typeEnv);
    }
}
