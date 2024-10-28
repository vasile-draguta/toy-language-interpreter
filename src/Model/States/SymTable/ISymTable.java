package Model.States.SymTable;

import Controller.AppException;
import Model.Values.IValue;

public interface ISymTable {
    void declareVar(String varName, IValue value) throws AppException;
    public IValue getValue(String varName) throws AppException;
    public void setValue(String varName, IValue value) throws AppException;
    public ISymTable copy() throws AppException;
    public String toString();
}
