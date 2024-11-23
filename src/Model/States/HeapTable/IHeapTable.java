package Model.States.HeapTable;

import Exceptions.HeapException;
import Model.Values.IValue;

public interface IHeapTable {
    public int allocate(IValue value);
    public void deallocate(int address) throws HeapException;
    public void write(int address, IValue value) throws HeapException;
    public IValue read(int address) throws HeapException;
}
