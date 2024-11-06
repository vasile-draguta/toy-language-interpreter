package Model.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{

    private final List<T> output;

    public MyList() {
        this.output = new ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        this.output.add(elem);
    }

    @Override
    public String toString() {
        return this.output.toString();
    }
}