package Model.Statement;

import Exceptions.StatementException;
import Model.States.ProgState;
import Model.Types.IType;

public class VarDeclStatement implements IStatement {
    private String id;
    private IType type;

    public VarDeclStatement(String id, IType type) {
        this.id = id;
        this.type = type;
    }

    public ProgState execute(ProgState state) throws StatementException {
        state.getSymTable().declareVar(id, type.defaultValue());
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclStatement(id, type);
    }

    @Override
    public String toString() {
        return type.toString() + " " + id;
    }
}