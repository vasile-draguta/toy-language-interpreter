package Model.States.HeapTable;

import Exceptions.HeapException;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;

import java.util.Map;

public interface IHeapTable {
    public void setHeap(Map<Integer, IValue> heap);
    public int allocate(IValue value);
    public void deallocate(int address) throws HeapException;
    public void write(int address, IValue value) throws HeapException;
    public IValue read(int address) throws HeapException;
    public Map<Integer, IValue> toMap();
    void clear();
}
