package Model.States;

import Model.Statement.IStatement;
import Model.States.ExecutionStack.IExecutionStack;
import Model.States.FileTable.IFileTable;
import Model.States.Output.IOutput;
import Model.States.SymTable.ISymTable;

public class ProgState {
    private final IExecutionStack executionStack;
    private final ISymTable symTable;
    private final IOutput output;
    private final IFileTable fileTable;
    private IStatement originalProgram;


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

    public ProgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IFileTable fileTable, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.fileTable = fileTable;
        this.originalProgram = originalProgram;
        executionStack.push(originalProgram);
    }

    public void reset() {
        executionStack.clear();
        symTable.clear();
        output.clear();
        fileTable.clear();
        executionStack.push(originalProgram);
    }

    @Override
    public String toString() {
        return "Execution Stack: " + executionStack.toString() + "\n" +
                "Symbol Table: " + symTable.toString() + "\n" +
                "Output: " + output.toString() + "\n" +
                "File Table: " + fileTable.toString() + "\n" +
                "-------------------\n";
    }


}
