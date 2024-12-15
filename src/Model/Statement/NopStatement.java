package Model.Statement;

import Exceptions.TypeException;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Utils.MyIDictionary;

public class NopStatement implements IStatement {
    @Override
    public ProgState execute(ProgState state) {
        return null;
    }

    @Override
    public String toString() {
        return "NOP";
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        return typeEnv;
    }

}
