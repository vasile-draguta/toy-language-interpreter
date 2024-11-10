package Model.Utils;
import java.util.ArrayList;

public interface MyIDictionary<K, V> {
    void put(K key, V value);
    V LookUp(K key);
    boolean isDefined(K key);
    ArrayList<K> getAllKeys();
    void remove(K key);
}
