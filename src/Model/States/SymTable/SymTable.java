package Model.States.SymTable;

import Exceptions.SymbolicTableException;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;

public class SymTable implements ISymTable {
    private final MyIDictionary<String, IValue> symTable;

    public SymTable() {
        this.symTable = new MyDictionary<String, IValue>();
    }

    @Override
    public void declareVar(String varName, IValue value) throws SymbolicTableException {
        if (symTable.isDefined(varName)) {
            throw new SymbolicTableException("Variable already declared!");
        }
        symTable.put(varName, value);
    }

    @Override
    public IValue getValue(String varName) throws SymbolicTableException {
        if (!symTable.isDefined(varName)) {
            throw new SymbolicTableException("Variable not defined!");
        }
        return symTable.LookUp(varName);
    }

    @Override
    public void setValue(String varName, IValue value) throws SymbolicTableException {
        if (!symTable.isDefined(varName)) {
            throw new SymbolicTableException("Variable not defined!");
        }
        symTable.put(varName, value);
    }

    @Override
    public ISymTable copy() {
        ISymTable newSymTable = new SymTable();

        for (String key : symTable.getAllKeys()) {
            newSymTable.declareVar(key, symTable.LookUp(key));
        }

        return newSymTable;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String key : symTable.getAllKeys()) {
            result.append(key).append(" -> ").append(symTable.LookUp(key));
            if (symTable.getAllKeys().indexOf(key) != symTable.getAllKeys().size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}
