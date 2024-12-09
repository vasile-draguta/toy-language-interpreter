package Model.Statement;

import Exceptions.StatementException;
import Model.States.ExecutionStack.ExecutionStack;
import Model.States.ProgState;

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
                innerStatement);
    }

    @Override
    public String toString(){
        return "fork(" + innerStatement.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(innerStatement.deepCopy());
    }
}
