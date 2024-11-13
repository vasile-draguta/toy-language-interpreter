package Hardcoded;

import Model.Expression.ArithmeticExpression;
import Model.Expression.RelationalExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Model.Statement.FileStatements.CloseFileStatement;
import Model.Statement.FileStatements.OpenFileStatement;
import Model.Statement.FileStatements.ReadFileStatement;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Types.StringType;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.StringValue;

import java.util.ArrayList;
import java.util.List;

public class Hardcoded {
    private final static ArrayList<IStatement> hardcodedPrograms = new ArrayList<>(List.of(
            //String code1 = "int v; v=2; Print(v);";

            new CompStatement(new VarDeclStatement("v", new IntegerType()),
                    new CompStatement(new AssignStatement("v", new ValueExpression(new IntegerValue(2))), new PrintStatement(new
                            VariableExpression("v")))),

            //String code2 = "int a; int b; a=2+3*5; b=a+1; Print(b);";

            new CompStatement(new VarDeclStatement("a", new IntegerType()),
                    new CompStatement(new VarDeclStatement("b", new IntegerType()),
                            new CompStatement(new AssignStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntegerValue(2)), new
                                    ArithmeticExpression("*", new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(5))))),
                                    new CompStatement(new AssignStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new
                                            IntegerValue(1)))), new PrintStatement(new VariableExpression("b")))))),


            //String code3 = "bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v);";

            new CompStatement(new VarDeclStatement("a", new BooleanType()),
                    new CompStatement(new VarDeclStatement("v", new IntegerType()),
                            new CompStatement(new AssignStatement("a", new ValueExpression(new BooleanValue(true))),
                                    new CompStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ValueExpression(new
                                            IntegerValue(2))), new AssignStatement("v", new ValueExpression(new IntegerValue(3)))), new PrintStatement(new
                                            VariableExpression("v")))))),

            //String code4 = "string varf; varf=\"test.in\"; openRFile(varf); int varc; readFile(varf, varc); Print(varc); readFile(varf, varc); Print(varc); closeRFile(varf);";

            new CompStatement(new VarDeclStatement("varf", new StringType()),
                    new CompStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                            new CompStatement(new OpenFileStatement(new VariableExpression("varf")),
                                    new CompStatement(new VarDeclStatement("varc", new IntegerType()),
                                            new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                    new CompStatement(new PrintStatement(new VariableExpression("varc")),
                                                            new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                    new CompStatement(new PrintStatement(new VariableExpression("varc")),
                                                                            new CloseFileStatement(new VariableExpression("varf")))))))))),

            //String code5 = "int a; int b; a=2; b=5; If a < b Then Print(a is less than b) Else Print(a is not less than b);";

            new CompStatement(new VarDeclStatement("a", new IntegerType()), new CompStatement(new VarDeclStatement("b", new IntegerType()),
                    new CompStatement(new AssignStatement("a", new ValueExpression(new IntegerValue(2))),
                            new CompStatement(new AssignStatement("b", new ValueExpression(new IntegerValue(5))),
                                    new IfStatement(new RelationalExpression(new VariableExpression("a"), new VariableExpression("b"), "<"),
                                            new PrintStatement(new ValueExpression(new StringValue("a is less than b"))),
                                            new PrintStatement(new ValueExpression(new StringValue("a is not less than b"))))))))


    ));

    public static ArrayList<IStatement> getHardcodedPrograms() {
        return hardcodedPrograms;
    }
}
