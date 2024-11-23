package Model.States.ExecutionStack;

import Exceptions.ExecutionStackException;
import Model.Statement.IStatement;
import Model.Utils.MyIStack;
import Model.Utils.MyStack;

import java.util.List;


public class ExecutionStack implements IExecutionStack {
    MyIStack<IStatement> stack;

    public ExecutionStack() {
        stack = new MyStack<>();
    }

    @Override
    public void push(IStatement statement) {
        stack.push(statement);
    }

    @Override
    public IStatement pop() throws ExecutionStackException {
        if (stack.isEmpty())
            throw new ExecutionStackException("Execution stack is empty");
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        MyIStack<IStatement> copy = new MyStack<>();
        try {
            while(!stack.isEmpty()) {
                IStatement statement = stack.pop();
                result.append(statement.toString()).append(" ");
                copy.push(statement);
            }
            while(!copy.isEmpty()) {
                stack.push(copy.pop());
            }
        }
        catch (ExecutionStackException e) {
            throw new ExecutionStackException("Execution stack is empty");
        }
        return result.toString();
    }

    @Override
    public IStatement peek() {
        return stack.peek();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public List<IStatement> getStatements() {
        return stack.toList();
    }


}
