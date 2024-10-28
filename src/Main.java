import Controller.Controller;
import Model.Expression.ArithmeticExpression;
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

        // int v; v=2; Print(v);

        IStatement ex1 =new CompStatement(new VarDeclStatement("v",new IntegerType()),
                new CompStatement(new AssignStatement("v",new ValueExpression(new IntegerValue(2))), new PrintStatement(new
                        VariableExpression("v"))));

        // int a; int b; a=2+3*5; b=a+1; Print(b)

        IStatement ex2 = new CompStatement(new VarDeclStatement("a",new IntegerType()),
                new CompStatement(new VarDeclStatement("b",new IntegerType()),
                        new CompStatement(new AssignStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntegerValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(5))))),
                                new CompStatement(new AssignStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new ValueExpression(new
                                        IntegerValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        ExecutionStack executionStack = new ExecutionStack();
        SymTable symTable = new SymTable();
        Output output = new Output();

        ProgState progState = new ProgState(executionStack, symTable, output, ex2);

        IRepository repository = new Repository();
        repository.addProgramState(progState);
        Controller controller = new Controller(repository, true);
        controller.allSteps();
    }
}