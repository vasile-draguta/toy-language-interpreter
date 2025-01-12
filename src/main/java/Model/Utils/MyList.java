package Model.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyList<T> implements MyIList<T>, Iterable<T> {

    private final List<T> output;

    public MyList() {
        this.output = new ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        this.output.add(elem);
    }

    public int size() {
        return this.output.size();
    }

    public T get(int index) {
        return this.output.get(index);
    }

    @Override
    public String toString() {
        return this.output.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                return get(index++);
            }
        };
    }

    @Override
    public int indexOf(T line) {
        return this.output.indexOf(line);
    }


}