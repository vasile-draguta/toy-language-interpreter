package Model.States;

import Model.Statement.IStatement;
import Model.States.ExecutionStack.ExecutionStack;
import Model.States.ExecutionStack.IExecutionStack;
import Model.States.Output.IOutput;
import Model.States.Output.Output;
import Model.States.SymTable.ISymTable;
import Model.States.SymTable.SymTable;

public class ProgState {
    private final IExecutionStack executionStack;
    private final ISymTable symTable;
    private final IOutput output;

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

    public ProgState(IExecutionStack executionStack, ISymTable symTable, IOutput output, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.output = output;
        this.originalProgram = originalProgram;
        executionStack.push(originalProgram);
    }

    @Override
    public String toString() {
        return "Execution Stack: " + executionStack.toString() + "\n" +
                "Symbol Table: " + symTable.toString() + "\n" +
                "Output: " + output.toString() + "\n" +
                "-------------------\n";
    }


}
