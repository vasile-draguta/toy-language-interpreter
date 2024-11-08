package Model.States.ExecutionStack;

import Exceptions.ExecutionStackException;
import Model.Statement.IStatement;
import Model.Utils.MyIStack;
import Model.Utils.MyStack;

import java.util.List;


public class ExecutionStack implements IExecutionStack {
    MyIStack<IStatement> stack;

    public ExecutionStack() {
        stack = new MyStack<IStatement>();
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
        return stack.toString();
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
    public List<IStatement> getStatements() {
        return stack.toList();
    }


}
