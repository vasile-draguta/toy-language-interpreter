package Model.Expression;

import Controller.AppException;
import Model.States.SymTable.SymTable;
import Model.Values.IValue;

public interface IExpression {
    IValue evaluate(SymTable symTable) throws AppException;
    String toString();
}
