package Model.Utils;

public interface MyIList<T> extends Iterable<T> {
    void add(T elem);
    int size();
    int indexOf(T line);
}
