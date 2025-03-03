package Model.States.SemaphoreTable;

import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTable implements ISemaphoreTable {
    private final MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphoreTable;
    private Integer nextFreeLocation;
    private final Lock lock;

    public SemaphoreTable() {
        semaphoreTable = new MyDictionary<>();
        nextFreeLocation = 1;
        lock = new ReentrantLock();
    }

    @Override
    public Integer createSemaphore(Integer num) {
        lock.lock();
        semaphoreTable.put(nextFreeLocation, new Pair<>(num, new ArrayList<>()));
        nextFreeLocation++;
        lock.unlock();
        return nextFreeLocation - 1;
    }

    @Override
    public void setSemaphore(Integer index, Pair<Integer, List<Integer>> value) {
        lock.lock();
        semaphoreTable.put(index, value);
        lock.unlock();
    }

    @Override
    public Pair<Integer, List<Integer>> getSemaphoreValue(Integer index) {
        return semaphoreTable.LookUp(index);
    }

    @Override
    public Map<Integer, Pair<Integer, String>> toMap() {
        Map<Integer, Pair<Integer, String>> map = new HashMap<>();
        for(Integer key : semaphoreTable.getAllKeys()) {
            Pair<Integer, List<Integer>> value = semaphoreTable.LookUp(key);
            map.put(key, new Pair<>(value.getKey(), value.getValue().toString()));
        }
        return map;
    }

    @Override
    public void clear() {
        semaphoreTable.clear();
        nextFreeLocation = 1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Integer key : semaphoreTable.getAllKeys()) {
            result.append(key.toString()).append(" -> ").append(semaphoreTable.LookUp(key).toString());
            if (semaphoreTable.getAllKeys().indexOf(key) != semaphoreTable.getAllKeys().size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}
