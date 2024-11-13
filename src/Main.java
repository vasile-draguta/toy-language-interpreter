import Controller.Controller;
import Hardcoded.Hardcoded;
import Model.Statement.IStatement;
import Model.States.ExecutionStack.ExecutionStack;
import Model.States.ExecutionStack.IExecutionStack;
import Model.States.FileTable.FileTable;
import Model.States.FileTable.IFileTable;
import Model.States.Output.IOutput;
import Model.States.Output.Output;
import Model.States.ProgState;
import Model.States.SymTable.ISymTable;
import Model.States.SymTable.SymTable;
import Repository.IRepository;
import Repository.Repository;
import View.Command.Command;
import View.Command.DisplayFlagCommand;
import View.Command.ExitCommand;
import View.Command.RunExampleCommand;
import View.TextMenu;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        IRepository repository = new Repository("log.txt");
        Controller controller = new Controller(repository, true);
        TextMenu menu = new TextMenu();

        ArrayList<IStatement> hardcodedPrograms = Hardcoded.getHardcodedPrograms();
        int key = 1;

        menu.addCommand(new ExitCommand("0", "Exit"));
        for (IStatement program : hardcodedPrograms) {
            IExecutionStack stack = new ExecutionStack();
            ISymTable symTable = new SymTable();
            IOutput output = new Output();
            IFileTable fileTable = new FileTable();
            ProgState state = new ProgState(stack, symTable, output, fileTable, program);
            Command command = new RunExampleCommand(Integer.toString(key), "Run example " + key, controller, state);
            menu.addCommand(command);
            key++;
        }
//        menu.addCommand(new DisplayFlagCommand("d", "Toggle display flag", controller));

        menu.show();
    }
}