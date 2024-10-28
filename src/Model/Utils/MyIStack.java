package Model.Utils;

import java.util.List;

public interface MyIStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    T peek();
    int size();
    List<T> toList();
    public  List<T> getReversed();
}