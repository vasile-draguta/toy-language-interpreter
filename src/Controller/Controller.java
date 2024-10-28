package Controller;

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
            System.out.println(state.toString());
        }
    }

    public Boolean getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(Boolean displayFlag) {
        this.displayFlag = displayFlag;
    }


}
