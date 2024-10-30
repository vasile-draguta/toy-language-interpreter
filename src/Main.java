import Controller.Controller;
import Model.Expression.ArithmeticExpression;
import Model.Expression.LogicExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Model.States.ExecutionStack.ExecutionStack;
import Model.States.Output.Output;
import Model.States.ProgState;
import Model.States.SymTable.SymTable;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Repository.IRepository;
import Repository.Repository;
import com.sun.jdi.Value;


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


        // bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)

        IStatement ex3 = new CompStatement(new VarDeclStatement("a",new BooleanType()),
                new CompStatement(new VarDeclStatement("v", new IntegerType()),
                        new CompStatement(new AssignStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new
                                        IntegerValue(2))), new AssignStatement("v", new ValueExpression(new IntegerValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));


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

//TODO add string type and value
//TODO add exceptions
//TODO add file handling