import Controller.Controller;
import Repository.IRepository;
import Repository.Repository;
import View.GUI.MainWindow;


public class Main {
    public static void main(String[] args) {

        IRepository repository = new Repository("log.txt");
        Controller controller = new Controller(repository,true);


//        TextMenu menu = new TextMenu();
//        ArrayList<Pair<String,IStatement>> hardcodedPrograms = Hardcoded.getHardcodedPrograms();
//        int key = 1;
//
//        menu.addCommand(new ExitCommand("0", "Exit"));
//        for (Pair<String, IStatement> program : hardcodedPrograms) {
//            IExecutionStack stack = new ExecutionStack();
//            ISymTable symTable = new SymTable();
//            IOutput output = new Output();
//            IFileTable fileTable = new FileTable();
//            IHeapTable heapTable = new HeapTable();
//            program.getValue().typeCheck(new MyDictionary<>());
//            ProgState state = new ProgState(stack, symTable, output, fileTable, heapTable, program.getValue());
//            Command command = new RunExampleCommand(Integer.toString(key), "Run: " + program.getKey(), controller, state);
//            menu.addCommand(command);
//            key++;
//        }
//
//        menu.show();

        MainWindow.setController(controller);
        MainWindow.run(args);
    }
}