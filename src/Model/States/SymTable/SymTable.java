package Model.States.SymTable;

import Controller.AppException;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;

public class SymTable implements ISymTable {
    private final MyIDictionary<String, IValue> symTable;

    public SymTable() {
        this.symTable = new MyDictionary<String, IValue>();
    }

    @Override
    public void declareVar(String varName, IValue value) throws AppException {
        if (symTable.isDefined(varName)) {
            throw new AppException("Variable already declared!");
        }
        symTable.put(varName, value);
    }

    @Override
    public IValue getValue(String varName) throws AppException {
        if (!symTable.isDefined(varName)) {
            throw new AppException("Variable not defined!");
        }
        return symTable.LookUp(varName);
    }

    @Override
    public void setValue(String varName, IValue value) throws AppException {
        if (!symTable.isDefined(varName)) {
            throw new AppException("Variable not defined!");
        }
        symTable.put(varName, value);
    }

    @Override
    public ISymTable copy() throws AppException {
        ISymTable newSymTable = new SymTable();

        for (String key : symTable.getAllKeys()) {
            newSymTable.declareVar(key, symTable.LookUp(key));
        }

        return newSymTable;
    }
}
