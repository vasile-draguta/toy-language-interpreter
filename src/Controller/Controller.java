package Controller;

import Exceptions.ExecutionStackException;
import Model.Statement.IStatement;
import Model.States.ExecutionStack.IExecutionStack;
import Model.States.ProgState;
import Repository.IRepository;

public class Controller {
    private IRepository repository;
    private Boolean displayFlag;

    public Controller(IRepository repository, Boolean displayFlag) {
        this.repository = repository;
        this.displayFlag = displayFlag;
    }

    public void displayOutput() {
        System.out.println(repository.getCurrentProgram().getOutput());
    }

    public void oneStep(ProgState state) throws ExecutionStackException {
        IExecutionStack stack = state.getExecutionStack();
        if (stack.isEmpty()) {
            throw new ExecutionStackException("Program state stack is empty");
        }

        IStatement currentStatement = stack.pop();
        currentStatement.execute(state);
    }

    public void allSteps() {
        ProgState state = repository.getCurrentProgram();
        System.out.println(state.toString());
        while (!state.getExecutionStack().isEmpty()) {
            oneStep(state);
            if (displayFlag) {
                System.out.println(state);
            }
        }
        displayOutput();
        repository.clearCompletedPrograms();
    }

    public void setDisplayFlag(Boolean displayFlag) {
        this.displayFlag = displayFlag;
    }
}
