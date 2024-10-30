package Model.Statement;

import Exceptions.StatementException;
import Model.States.ProgState;

public class CompStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgState execute(ProgState state) throws StatementException {
        state.getExecutionStack().push(second);
        state.getExecutionStack().push(first);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new CompStatement(first.deepCopy(), second.deepCopy());
    }

    @Override
    public String toString() {
        return "(" + first.toString() + "; " + second.toString() + ")";
    }
}
