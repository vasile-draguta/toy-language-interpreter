package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Utils.MyIDictionary;

public class VarDeclStatement implements IStatement {
    private final String id;
    private final IType type;

    public VarDeclStatement(String id, IType type) {
        this.id = id;
        this.type = type;
    }

    public ProgState execute(ProgState state) throws StatementException {
        state.getSymTable().declareVar(id, type.defaultValue());
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclStatement(id, type);
    }

    @Override
    public String toString() {
        return type.toString() + " " + id;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        typeEnv.put(id, type);
        return typeEnv;
    }
}