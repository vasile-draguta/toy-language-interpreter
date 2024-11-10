package View.Command;

import Controller.Controller;

public class DisplayFlagCommand extends Command {
    Controller controller;
    public DisplayFlagCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
       Boolean flag = controller.getDisplayFlag();
       controller.setDisplayFlag(!flag);
        System.out.println("Flag is set to " + !flag);
    }
}
