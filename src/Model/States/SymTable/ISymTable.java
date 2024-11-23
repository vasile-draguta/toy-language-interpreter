package Model.States.SymTable;

import Exceptions.SymbolicTableException;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;

import java.util.Map;

public interface ISymTable {
    void declareVar(String varName, IValue value) throws SymbolicTableException;
    public IValue getValue(String varName) throws SymbolicTableException;
    public void setValue(String varName, IValue value) throws SymbolicTableException;
    public ISymTable copy();
    public String toString();
    public void clear();
    public Map<String, IValue> toMap();
}
