package Model.Statement;

import Model.States.ProgState;

public class NopStatement implements IStatement {
    @Override
    public ProgState execute(ProgState state) {
        return state;
    }

    @Override
    public String toString() {
        return "NOP";
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }
}
