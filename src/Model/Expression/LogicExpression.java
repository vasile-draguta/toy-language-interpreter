package Model.Expression;

import Exceptions.TypeException;
import Model.States.ProgState;
import Model.Types.BooleanType;
import Model.Types.IType;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;

public class LogicExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final String operator;

    public LogicExpression(String operator, IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(ProgState state) {
        return left.evaluate(state).compose(right.evaluate(state), operator);
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExpression(operator, left.deepCopy(), right.deepCopy());
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType type1 = left.typeCheck(typeEnv);
        IType type2 = right.typeCheck(typeEnv);

        if(type1.equals(new BooleanType())) {
            if(type2.equals(new BooleanType())) {
                return new BooleanType();
            } else {
                throw new TypeException("Second operand is not a boolean");
            }
        } else {
            throw new TypeException("First operand is not a boolean");
        }
    }
}
