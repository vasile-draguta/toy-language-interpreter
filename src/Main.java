import Controller.Controller;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Model.States.ExecutionStack.ExecutionStack;
import Model.States.Output.Output;
import Model.States.ProgState;
import Model.States.SymTable.SymTable;
import Model.Types.IntegerType;
import Model.Values.IntegerValue;
import Repository.IRepository;
import Repository.Repository;


public class Main {
    public static void main(String[] args) {

        // int v;
        // v=2;
        // Print(v);

        IStatement ex1 =new CompStatement(new VarDeclStatement("v",new IntegerType()),
                new CompStatement(new AssignStatement("v",new ValueExpression(new IntegerValue(2))), new PrintStatement(new
                        VariableExpression("v"))));

        ExecutionStack executionStack = new ExecutionStack();
        SymTable symTable = new SymTable();
        Output output = new Output();

        ProgState progState = new ProgState(executionStack, symTable, output, ex1);

        IRepository repository = new Repository();
        repository.addProgramState(progState);
        Controller controller = new Controller(repository, true);
        controller.allSteps();
    }
}