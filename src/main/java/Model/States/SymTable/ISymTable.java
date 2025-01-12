package Model.States.SymTable;

import Exceptions.SymbolicTableException;
import Model.Values.IValue;

import java.util.Map;

public interface ISymTable {
    void declareVar(String varName, IValue value) throws SymbolicTableException;
    IValue getValue(String varName) throws SymbolicTableException;
    void setValue(String varName, IValue value) throws SymbolicTableException;
    ISymTable copy();
    String toString();
    void clear();
    Map<String, IValue> toMap();
}
