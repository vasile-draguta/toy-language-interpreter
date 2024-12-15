package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Utils.MyIDictionary;

public interface IStatement {
    ProgState execute(ProgState state) throws StatementException;
    String toString();
    IStatement deepCopy();
    MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException;
}
