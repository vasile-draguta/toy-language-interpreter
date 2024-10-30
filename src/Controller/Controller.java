package Controller;

import Exceptions.AppException;
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
        System.out.println(repository.getCurrentProgram().getOutput().toString());
    }

    public ProgState oneStep(ProgState state) throws AppException {
        IExecutionStack stack = state.getExecutionStack();
        if (stack.isEmpty()) {
            throw new AppException("Program state stack is empty");
        }

        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allSteps() throws AppException {
        ProgState state = repository.getCurrentProgram();
        System.out.println(state.toString());
        while (!state.getExecutionStack().isEmpty()) {
            oneStep(state);
            if (displayFlag) {
                System.out.println(state);
            }
        }
        displayOutput();
    }
}
