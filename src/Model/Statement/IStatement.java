package Model.Statement;

import Controller.AppException;
import Model.States.ProgState;

public interface IStatement {
    ProgState execute(ProgState state) throws AppException;
    String toString();
    public IStatement deepCopy();
}
