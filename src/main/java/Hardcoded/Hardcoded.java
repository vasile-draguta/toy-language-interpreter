package Hardcoded;

import Model.Expression.*;
import Model.Statement.*;
import Model.Statement.FileStatements.CloseFileStatement;
import Model.Statement.FileStatements.OpenFileStatement;
import Model.Statement.FileStatements.ReadFileStatement;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Utils.Pair;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.StringValue;

import java.util.ArrayList;
import java.util.List;

public class Hardcoded {
    public final static ArrayList<Pair<String, IStatement>> hardcodedPrograms = new ArrayList<>(List.of(
            new Pair<>("int v; v=2; Print(v);",
                    new CompStatement(new VarDeclStatement("v", new IntegerType()),
                            new CompStatement(new AssignStatement("v", new ValueExpression(new IntegerValue(2))),
                                    new PrintStatement(new VariableExpression("v"))))),

            new Pair<>("int a; int b; a=2+3*5; b=a+1; Print(b);",
                    new CompStatement(new VarDeclStatement("a", new IntegerType()),
                            new CompStatement(new VarDeclStatement("b", new IntegerType()),
                                    new CompStatement(new AssignStatement("a", new ArithmeticExpression("+",
                                            new ValueExpression(new IntegerValue(2)),
                                            new ArithmeticExpression("*",
                                                    new ValueExpression(new IntegerValue(3)),
                                                    new ValueExpression(new IntegerValue(5))))),
                                            new CompStatement(new AssignStatement("b",
                                                    new ArithmeticExpression("+",
                                                            new VariableExpression("a"),
                                                            new ValueExpression(new IntegerValue(1)))),
                                                    new PrintStatement(new VariableExpression("b"))))))),

            new Pair<>("bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v);",
                    new CompStatement(new VarDeclStatement("a", new BooleanType()),
                            new CompStatement(new VarDeclStatement("v", new IntegerType()),
                                    new CompStatement(new AssignStatement("a", new ValueExpression(new BooleanValue(true))),
                                            new CompStatement(new IfStatement(
                                                    new VariableExpression("a"),
                                                    new AssignStatement("v", new ValueExpression(new IntegerValue(2))),
                                                    new AssignStatement("v", new ValueExpression(new IntegerValue(3)))),
                                                    new PrintStatement(new VariableExpression("v"))))))),

            new Pair<>("string varf; varf=\"test.in\"; openRFile(varf); int varc; readFile(varf, varc); Print(varc); readFile(varf, varc); Print(varc); closeRFile(varf);",
                    new CompStatement(new VarDeclStatement("varf", new StringType()),
                            new CompStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                                    new CompStatement(new OpenFileStatement(new VariableExpression("varf")),
                                            new CompStatement(new VarDeclStatement("varc", new IntegerType()),
                                                    new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                            new CompStatement(new PrintStatement(new VariableExpression("varc")),
                                                                    new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                            new CompStatement(new PrintStatement(new VariableExpression("varc")),
                                                                                    new CloseFileStatement(new VariableExpression("varf"))))))))))),

            new Pair<>("int a; int b; a=2; b=5; If a < b Then Print(a is less than b) Else Print(a is not less than b);",
                    new CompStatement(new VarDeclStatement("a", new IntegerType()),
                            new CompStatement(new VarDeclStatement("b", new IntegerType()),
                                    new CompStatement(new AssignStatement("a", new ValueExpression(new IntegerValue(2))),
                                            new CompStatement(new AssignStatement("b", new ValueExpression(new IntegerValue(5))),
                                                    new IfStatement(new RelationalExpression(
                                                            new VariableExpression("a"),
                                                            new VariableExpression("b"),
                                                            "<"),
                                                            new PrintStatement(new ValueExpression(new StringValue("a is less than b"))),
                                                            new PrintStatement(new ValueExpression(new StringValue("a is not less than b"))))))))),

            new Pair<>("Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a);",
                    new CompStatement(new VarDeclStatement("v", new RefType(new IntegerType())),
                            new CompStatement(new NewStatement("v", new ValueExpression(new IntegerValue(20))),
                                    new CompStatement(new VarDeclStatement("a", new RefType(new RefType(new IntegerType()))),
                                            new CompStatement(new NewStatement("a", new VariableExpression("v")),
                                                    new CompStatement(new PrintStatement(new VariableExpression("v")),
                                                            new PrintStatement(new VariableExpression("a")))))))),

            new Pair<>("Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)",
                    new CompStatement(new VarDeclStatement("v", new RefType(new IntegerType())),
                            new CompStatement(new NewStatement("v", new ValueExpression(new IntegerValue(20))),
                                    new CompStatement(new VarDeclStatement("a", new RefType(new RefType(new IntegerType()))),
                                            new CompStatement(new NewStatement("a", new VariableExpression("v")),
                                                    new CompStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                            new PrintStatement(new ArithmeticExpression("+",
                                                                    new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
                                                                    new ValueExpression(new IntegerValue(5)))))))))),

            new Pair<>("Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);",
                    new CompStatement(new VarDeclStatement("v", new RefType(new IntegerType())),
                            new CompStatement(new NewStatement("v", new ValueExpression(new IntegerValue(20))),
                                    new CompStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                            new CompStatement(new WriteHeapStatement("v", new ValueExpression(new IntegerValue(30))),
                                                    new PrintStatement(new ArithmeticExpression("+",
                                                            new ReadHeapExpression(new VariableExpression("v")),
                                                            new ValueExpression(new IntegerValue(5))))))))),

            new Pair<>("Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))",
                    new CompStatement(new VarDeclStatement("v", new RefType(new IntegerType())),
                            new CompStatement(new NewStatement("v", new ValueExpression(new IntegerValue(20))),
                                    new CompStatement(new VarDeclStatement("a", new RefType(new RefType(new IntegerType()))),
                                            new CompStatement(new NewStatement("a", new VariableExpression("v")),
                                                    new CompStatement(new NewStatement("v", new ValueExpression(new IntegerValue(30))),
                                                            new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))))),

            new Pair<>("int v; v=4; (while (v>0) print(v);v=v-1); print(v)",
                    new CompStatement(new VarDeclStatement("v", new IntegerType()),
                            new CompStatement(new AssignStatement("v", new ValueExpression(new IntegerValue(4))),
                                    new CompStatement(new WhileStatement(new RelationalExpression(
                                            new VariableExpression("v"),
                                            new ValueExpression(new IntegerValue(0)),
                                            ">"),
                                            new CompStatement(new PrintStatement(new VariableExpression("v")),
                                                    new AssignStatement("v", new ArithmeticExpression("-",
                                                            new VariableExpression("v"),
                                                            new ValueExpression(new IntegerValue(1)))))),
                                            new PrintStatement(new VariableExpression("v")))))),

            new Pair<>("int v; Ref int a; v=10; new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))",
                    new CompStatement(new VarDeclStatement("v",new IntegerType()),
                            new CompStatement(new VarDeclStatement("a",new RefType(new IntegerType())),
                                    new CompStatement(new AssignStatement("v",new ValueExpression(new IntegerValue(10))),
                                            new CompStatement(new NewStatement("a", new ValueExpression(new IntegerValue(22))),
                                                    new CompStatement(new ForkStatement(
                                                            new CompStatement(new WriteHeapStatement("a", new ValueExpression(new IntegerValue(30))),
                                                                    new CompStatement(new AssignStatement("v",new ValueExpression(new IntegerValue(32))),
                                                                            new CompStatement(new PrintStatement(new VariableExpression("v")),
                                                                                    new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
                                                            new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))))),

            new Pair<>("int a; int b; int c; a=1; b=2; c=5; switch(a*10) (case (b*c) print(a); print(b); case (10) print(100); print(200); default print(300)); print(300)",
                    new CompStatement(new VarDeclStatement("a", new IntegerType()),
                            new CompStatement(new VarDeclStatement("b", new IntegerType()),
                                    new CompStatement(new VarDeclStatement("c", new IntegerType()),
                                            new CompStatement(new AssignStatement("a", new ValueExpression(new IntegerValue(1))),
                                                    new CompStatement(new AssignStatement("b", new ValueExpression(new IntegerValue(2))),
                                                            new CompStatement(new AssignStatement("c", new ValueExpression(new IntegerValue(5))),
                                                                    new CompStatement(new SwitchStatement(new ArithmeticExpression("*",
                                                                            new VariableExpression("a"),
                                                                            new ValueExpression(new IntegerValue(10))),
                                                                            new ArithmeticExpression("*",
                                                                                    new VariableExpression("b"),
                                                                                    new VariableExpression("c")),
                                                                            new CompStatement(new PrintStatement(new VariableExpression("a")),
                                                                                    new PrintStatement(new VariableExpression("b"))),
                                                                            new ValueExpression(new IntegerValue(10)),
                                                                            new CompStatement(new PrintStatement(new ValueExpression(new IntegerValue(100))),
                                                                                    new PrintStatement(new ValueExpression(new IntegerValue(200)))),
                                                                            new PrintStatement(new ValueExpression(new IntegerValue(300)))),
                                                                            new PrintStatement(new ValueExpression(new IntegerValue(300))))))))))),

            new Pair<>("Ref int v1, int cnt; new(v1, 1); createSemaphore(cnt, rH(v1)); fork(acquire(cnt); wH(v1, rH(v1) * 10); print(rH(v1)); release(cnt)); fork(acquire(cnt); wH(v1, rH(v1) * 10); wH(v1, rH(v1) * 2); print(rH(v1)); release(cnt)); acquire(cnt); print(rH(v1) - 1); release(cnt);",
                    new CompStatement(new VarDeclStatement("v1", new RefType(new IntegerType())),
                            new CompStatement(new VarDeclStatement("cnt", new IntegerType()),
                                    new CompStatement(new NewStatement("v1", new ValueExpression(new IntegerValue(1))),
                                            new CompStatement(new CreateSemaphoreStatement("cnt", new ReadHeapExpression(new VariableExpression("v1"))),
                                                    new CompStatement(new ForkStatement(
                                                            new CompStatement(new AcquireStatement("cnt"),
                                                                    new CompStatement(new WriteHeapStatement("v1", new ArithmeticExpression("*",
                                                                            new ReadHeapExpression(new VariableExpression("v1")),
                                                                            new ValueExpression(new IntegerValue(10)))),
                                                                            new CompStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                    new ReleaseStatement("cnt"))))),
                                                            new CompStatement(new ForkStatement(
                                                                    new CompStatement(new AcquireStatement("cnt"),
                                                                            new CompStatement(new WriteHeapStatement("v1", new ArithmeticExpression("*",
                                                                                    new ReadHeapExpression(new VariableExpression("v1")),
                                                                                    new ValueExpression(new IntegerValue(10)))),
                                                                                    new CompStatement(new WriteHeapStatement("v1", new ArithmeticExpression("*",
                                                                                            new ReadHeapExpression(new VariableExpression("v1")),
                                                                                            new ValueExpression(new IntegerValue(2)))),
                                                                                            new CompStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                                    new ReleaseStatement("cnt")))))),
                                                                    new CompStatement(new AcquireStatement("cnt"),
                                                                            new CompStatement(new PrintStatement(new ArithmeticExpression("-", new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntegerValue(1)))),
                                                                                    new ReleaseStatement("cnt"))))))))))
            ));

    public static ArrayList<Pair<String, IStatement>> getHardcodedPrograms() {
        return hardcodedPrograms;
    }
}