package Model.States;

import Exceptions.ExecutionStackException;
import Model.Statement.IStatement;
import Model.States.ExecutionStack.IExecutionStack;
import Model.States.FileTable.IFileTable;
import Model.States.HeapTable.IHeapTable;
import Model.States.Output.IOutput;
import Model.States.SymTable.ISymTable;


public class ProgState {
    private Integer id;
    private static Integer nextId = 0;
    private final IExecutionStack executionStack;
    private final ISymTable symTable;
    private final IOutput output;
    private final IFileTable fileTable;
    private final IHeapTable heapTable;
    private final IStatement originalProgram;


    public IExecutionStack getExecutionStack() {
        return executionStack;
    }

    public ISymTable getSymTable() {
        return symTable;
    }

    public IOutput getOutput() {
        return output;
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public IHeapTable getHeapTable() {
        return heapTable;
    }

    public ProgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IFileTable fileTable, IHeapTable heapTable, IStatement originalProgram) {
        this.id = getId();
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.fileTable = fileTable;
        this.originalProgram = originalProgram;
        this.heapTable = heapTable;
        executionStack.push(originalProgram);
    }

    public synchronized Integer getId() {
        return ++nextId;
    }

    public void reset() {
        executionStack.clear();
        symTable.clear();
        output.clear();
        fileTable.clear();
        heapTable.clear();
        executionStack.push(originalProgram);
    }

    public Boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public ProgState oneStep() throws ExecutionStackException {
        if (executionStack.isEmpty()) {
            throw new ExecutionStackException("Execution stack is empty");
        }
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Execution Stack: " + executionStack.toString() + "\n" +
                "Symbol Table: " + symTable.toString() + "\n" +
                "Output: " + output.toString() + "\n" +
                "File Table: " + fileTable.toString() + "\n" +
                "Heap Table: " + heapTable.toString() + "\n" +
                "-------------------\n";
    }
}
