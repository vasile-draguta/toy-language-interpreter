package View.Command;

import Controller.Controller;
import Model.States.ProgState;

public class RunExampleCommand extends Command {
    private final Controller controller;
    private final ProgState state;

    public RunExampleCommand(String key, String description, Controller controller, ProgState state) {
        super(key, description);
        this.controller = controller;
        this.state = state;
    }


    @Override
    public void execute() {
        try {
            controller.addProgram(state);
            controller.allSteps();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
