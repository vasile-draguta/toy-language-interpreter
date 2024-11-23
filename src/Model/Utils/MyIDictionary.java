package Model.Utils;
import java.util.ArrayList;
import java.util.Map;

public interface MyIDictionary<K, V> {
    void put(K key, V value);
    V LookUp(K key);
    boolean isDefined(K key);
    ArrayList<K> getAllKeys();
    void remove(K key);
    void clear();
    Map<K, V> toMap();
    void setDictionary(Map<K, V> map);
}
