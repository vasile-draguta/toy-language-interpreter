package Model.Expression;

import Exceptions.ExpressionException;
import Exceptions.TypeException;
import Model.States.ProgState;
import Model.Types.BooleanType;
import Model.Types.IType;
import Model.Types.IntegerType;
import Model.Utils.MyIDictionary;
import Model.Values.BooleanValue;
import Model.Values.IValue;
import Model.Values.IntegerValue;

public class RelationalExpression implements IExpression {
    private final IExpression expression1, expression2;
    private final String operator;

    public RelationalExpression(IExpression expression1, IExpression expression2, String operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(expression1.deepCopy(), expression2.deepCopy(), operator);
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operator + " " + expression2.toString();
    }

    @Override
    public IValue evaluate(ProgState state) {
        IValue value1 = expression1.evaluate(state);
        IValue value2 = expression2.evaluate(state);

        if (!value1.getType().equals(value2.getType())) {
            throw new ExpressionException("Type mismatch");
        }

        if (!(value1 instanceof IntegerValue)) {
            throw new ExpressionException("Type mismatch");
        }

        return switch (operator) {
            case ">" ->
                    ((BooleanValue) value1.compose(value2, ">")).getValue() ? new BooleanValue(true) : new BooleanValue(false);
            case "<" ->
                    ((BooleanValue) value1.compose(value2, "<")).getValue() ? new BooleanValue(true) : new BooleanValue(false);
            case ">=" ->
                    ((BooleanValue) value1.compose(value2, ">=")).getValue() ? new BooleanValue(true) : new BooleanValue(false);
            case "<=" ->
                    ((BooleanValue) value1.compose(value2, "<=")).getValue() ? new BooleanValue(true) : new BooleanValue(false);
            case "==" ->
                    ((BooleanValue) value1.compose(value2, "==")).getValue() ? new BooleanValue(true) : new BooleanValue(false);
            case "!=" ->
                    ((BooleanValue) value1.compose(value2, "!=")).getValue() ? new BooleanValue(true) : new BooleanValue(false);
            default -> throw new ExpressionException("Invalid operator");
        };
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException {
        IType type1 = expression1.typeCheck(typeEnv);
        IType type2 = expression2.typeCheck(typeEnv);

        if(type1.equals(new IntegerType())) {
            if(type2.equals(new IntegerType())) {
                return new BooleanType();
            } else {
                throw new TypeException("Second operand is not an integer");
            }
        } else {
            throw new TypeException("First operand is not an integer");
        }
    }
}
