package Model.States.HeapTable;

import Exceptions.HeapException;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;

import java.util.Map;

public class HeapTable implements IHeapTable {
    private final MyIDictionary<Integer, IValue> heap;
    Integer nextFreeAddress;

    public HeapTable() {
        heap = new MyDictionary<>();
        nextFreeAddress = 1;
    }

    @Override
    public int allocate(IValue value) {
        heap.put(nextFreeAddress, value);
        nextFreeAddress++;
        return nextFreeAddress - 1;
    }

    @Override
    public void deallocate(int address) {
        try {
            heap.remove(address);
        } catch (Exception e) {
            throw new HeapException("Address not found in heap table");
        }
    }

    @Override
    public IValue read(int address) {
        try {
            return heap.LookUp(address);
        } catch (Exception e) {
            throw new HeapException("Address not found in heap table");
        }
    }

    @Override
    public void write(int address, IValue value) {
        if (heap.isDefined(address)) {
            throw new HeapException("Address already exists in heap table");
        }

        heap.put(address, value);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Integer key : heap.getAllKeys()) {
            result.append(key.toString()).append(" -> ").append(heap.LookUp(key).toString());
            if (heap.getAllKeys().indexOf(key) != heap.getAllKeys().size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    @Override
    public Map<Integer, IValue> toMap() {
        return heap.toMap();
    }

    @Override
    public void clear() {
        heap.clear();
    }
}
