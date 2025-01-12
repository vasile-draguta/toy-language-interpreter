package Model.States.ExecutionStack;

import Model.Statement.IStatement;
import Exceptions.ExecutionStackException;

import java.util.List;

public interface IExecutionStack {
    void push(IStatement statement);
    IStatement pop() throws ExecutionStackException;
    boolean isEmpty();
    int size();
    String toString();
    IStatement peek();
    List<IStatement> getStatements();
    void clear();
    List<String> getStatementsString();
}
