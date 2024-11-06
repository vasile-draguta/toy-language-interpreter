package View;

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

public class View {
    public static void run() {
        String code1 = "int v; v=2; Print(v);";

        IStatement ex1 = new CompStatement(new VarDeclStatement("v", new IntegerType()),
                new CompStatement(new AssignStatement("v", new ValueExpression(new IntegerValue(2))), new PrintStatement(new
                        VariableExpression("v"))));

        String code2 = "int a; int b; a=2+3*5; b=a+1; Print(b);";

        IStatement ex2 = new CompStatement(new VarDeclStatement("a", new IntegerType()),
                new CompStatement(new VarDeclStatement("b", new IntegerType()),
                        new CompStatement(new AssignStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntegerValue(2)), new
                                ArithmeticExpression("*", new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(5))))),
                                new CompStatement(new AssignStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new
                                        IntegerValue(1)))), new PrintStatement(new VariableExpression("b"))))));


        String code3 = "bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v);";

        IStatement ex3 = new CompStatement(new VarDeclStatement("a", new BooleanType()),
                new CompStatement(new VarDeclStatement("v", new IntegerType()),
                        new CompStatement(new AssignStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ValueExpression(new
                                        IntegerValue(2))), new AssignStatement("v", new ValueExpression(new IntegerValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));


        boolean menuIsRunning = true;

        ExecutionStack executionStack = new ExecutionStack();
        SymTable symTable = new SymTable();
        Output output = new Output();
        IRepository repository = new Repository();
        Controller controller = new Controller(repository, true);


        while(menuIsRunning) {
            System.out.println("1. Run example 1: " + code1);
            System.out.println("2. Run example 2: " + code2);
            System.out.println("3. Run example 3: " + code3);
            System.out.println("0. Exit");
            System.out.println("Choose an option: ");
            String option;
            option = System.console().readLine();
            IStatement ex = null;

            switch (option) {
                case "1":
                    ex = ex1;
                    break;
                case "2":
                    ex = ex2;
                    break;
                case "3":
                    ex = ex3;
                    break;
                case "0":
                    menuIsRunning = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

            if(ex != null) {
                ProgState progState = new ProgState(executionStack, symTable, output, ex);
                repository.addProgramState(progState);
                controller.allSteps();
                symTable.clear();
                output.clear();
            }
        }

    }
}