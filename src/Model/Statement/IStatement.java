package Model.Statement;

import Exceptions.StatementException;
import Model.States.ProgState;

public interface IStatement {
    ProgState execute(ProgState state) throws StatementException;
    String toString();
    public IStatement deepCopy();
}
