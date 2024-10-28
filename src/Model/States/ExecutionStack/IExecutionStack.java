package Model.States.ExecutionStack;

import Model.Utils.MyIStack;
import Model.Statement.IStatement;

import java.util.List;

public interface IExecutionStack {
    void push(IStatement statement);
    IStatement pop();
    boolean isEmpty();
    int size();
    String toString();
    IStatement peek();
    List<IStatement> getStatements();
}
