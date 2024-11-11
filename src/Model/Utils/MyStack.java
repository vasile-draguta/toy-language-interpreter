package Model.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private final Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public T pop() {
        return this.stack.pop();
    }

    @Override
    public void push(T v) {
        this.stack.push(v);
    }
    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public String toString() {
        return this.stack.toString();
    }

    public T peek() {
        return this.stack.peek();
    }

    public int size() {
        return this.stack.size();
    }

    @Override
    public List<T> toList() {
        return this.stack.stream().toList();
    }

    @Override
    public void clear() {
        this.stack.clear();
    }

    @Override
    public List<T> getReversed() {
        List<T> reversed = Arrays.asList((T[]) this.stack.toArray());

        return reversed;
    }
}
