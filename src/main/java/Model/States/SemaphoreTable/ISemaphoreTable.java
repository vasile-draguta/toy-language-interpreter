package Model.States.SemaphoreTable;

import Model.Utils.Pair;

import java.util.List;
import java.util.Map;

public interface ISemaphoreTable {
    Integer createSemaphore(Integer num);
    void setSemaphore(Integer index, Pair<Integer, List<Integer>> value);
    Pair<Integer, List<Integer>> getSemaphoreValue(Integer index);
    Map<Integer, Pair<Integer, String>> toMap();
    void clear();
}
