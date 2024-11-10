package Model.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private final Map<K, V> map;

    public MyDictionary() {
        this.map = new HashMap<K, V>();
    }

    @Override
    public void put(K key, V value) {
        this.map.put(key, value);
    }

    @Override
    public V LookUp(K key) {
        return this.map.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return this.map.get(key) != null;
    }

    @Override
    public String toString() {
        return this.map.toString();
    }

    @Override
    public ArrayList<K> getAllKeys() {
        return new ArrayList<K>(this.map.keySet());
    }

    @Override
    public void remove(K key) {
        this.map.remove(key);
    }
}
