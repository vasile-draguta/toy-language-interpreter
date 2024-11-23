package Model.States.HeapTable;

import Exceptions.HeapException;
import Model.Values.IValue;

import java.util.Map;

public interface IHeapTable {
    int allocate(IValue value);
    void deallocate(int address) throws HeapException;
    void write(int address, IValue value) throws HeapException;
    IValue read(int address) throws HeapException;
    Map<Integer, IValue> toMap();
    void clear();
}
