package Controller;

import Model.Statement.IStatement;
import Model.States.ExecutionStack.IExecutionStack;
import Model.States.ProgState;
import Repository.IRepository;

public class Controller {
    private IRepository repository;
    private Boolean displayFlag = false;

    public Controller(IRepository repository) {
        this.repository = repository;
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
        while (!state.getExecutionStack().isEmpty()) {
            oneStep(state);
            displayState(state);
        }
    }

    public Boolean getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(Boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    private void displayState(ProgState state) {
        if(displayFlag) {
            System.out.println(state);
        }
    }

}
