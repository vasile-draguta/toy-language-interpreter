import Controller.Controller;
import Hardcoded.Hardcoded;
import Model.Statement.IStatement;
import Model.States.ExecutionStack.ExecutionStack;
import Model.States.ExecutionStack.IExecutionStack;
import Model.States.FileTable.FileTable;
import Model.States.FileTable.IFileTable;
import Model.States.HeapTable.HeapTable;
import Model.States.HeapTable.IHeapTable;
import Model.States.Output.IOutput;
import Model.States.Output.Output;
import Model.States.ProgState;
import Model.States.SymTable.ISymTable;
import Model.States.SymTable.SymTable;
import Model.Utils.MyDictionary;
import Model.Utils.Pair;
import Repository.IRepository;
import Repository.Repository;
import View.Command.Command;
import View.Command.ExitCommand;
import View.Command.RunExampleCommand;
import View.TextMenu;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        IRepository repository = new Repository("log.txt");
        Controller controller = new Controller(repository,true);
        TextMenu menu = new TextMenu();

        ArrayList<Pair<String,IStatement>> hardcodedPrograms = Hardcoded.getHardcodedPrograms();
        int key = 1;

        menu.addCommand(new ExitCommand("0", "Exit"));
        for (Pair<String, IStatement> program : hardcodedPrograms) {
            IExecutionStack stack = new ExecutionStack();
            ISymTable symTable = new SymTable();
            IOutput output = new Output();
            IFileTable fileTable = new FileTable();
            IHeapTable heapTable = new HeapTable();
            program.getValue().typeCheck(new MyDictionary<>());
            ProgState state = new ProgState(stack, symTable, output, fileTable, heapTable, program.getValue());
            Command command = new RunExampleCommand(Integer.toString(key), "Run: " + program.getKey(), controller, state);
            menu.addCommand(command);
            key++;
        }

        menu.show();
    }
}