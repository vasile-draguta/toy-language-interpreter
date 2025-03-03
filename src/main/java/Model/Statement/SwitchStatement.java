package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypeException;
import Model.Expression.IExpression;
import Model.Expression.RelationalExpression;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Utils.MyIDictionary;

public class SwitchStatement implements IStatement {
    IExpression exp;
    IExpression exp1;
    IExpression exp2;
    IStatement stm1;
    IStatement stm2;
    IStatement stm3;

    public SwitchStatement(IExpression exp, IExpression exp1, IStatement stm1, IExpression exp2, IStatement stm2, IStatement stm3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stm1 = stm1;
        this.stm2 = stm2;
        this.stm3 = stm3;
    }


    @Override
    public ProgState execute(ProgState state) throws StatementException {
        IStatement newStm = new IfStatement(new RelationalExpression(exp, exp1, "=="), stm1, new IfStatement(new RelationalExpression(exp, exp2, "=="), stm2, stm3));
        state.getExecutionStack().push(newStm);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new SwitchStatement(exp.deepCopy(), exp1.deepCopy(), stm1.deepCopy(), exp2.deepCopy(), stm2.deepCopy(), stm3.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        stm1.typeCheck(typeEnv);
        stm2.typeCheck(typeEnv);
        stm3.typeCheck(typeEnv);
        IType val = exp.typeCheck(typeEnv);
        IType val1 = exp1.typeCheck(typeEnv);
        IType val2 = exp2.typeCheck(typeEnv);
        if (!val.equals(val1) || !val.equals(val2))
            throw new TypeException("Expressions in switch statement have different types");

        return typeEnv;
    }

    @Override
    public String toString() {
        return "switch (" + exp.toString() + ") case (" + exp1.toString() + "): " + stm1.toString() + " case (" + exp2.toString() + "): " + stm2.toString() + " default: " + stm3.toString();

    }
}
