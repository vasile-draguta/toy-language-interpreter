package Model.Values;

import Exceptions.ExpressionException;
import Model.Types.IType;
import Model.Types.IntegerType;

public class IntegerValue implements IValue{
    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean equals(Object other) {
        if (other instanceof IntegerValue) {
            return ((IntegerValue) other).getValue() == value;
        }
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public IValue deepCopy() {
        return new IntegerValue(value);
    }

    @Override
    public IValue compose(IValue other, String operator) throws ExpressionException {
        if (other instanceof IntegerValue) {
            int otherValue = ((IntegerValue) other).getValue();

            if( operator.equals("/") && otherValue == 0)
                throw new ExpressionException("Division by zero");

            return switch (operator) {
                case "+" -> new IntegerValue(value + otherValue);
                case "-" -> new IntegerValue(value - otherValue);
                case "*" -> new IntegerValue(value * otherValue);
                case "/" -> new IntegerValue(value / otherValue);
                case ">" -> new BooleanValue(value > otherValue);
                case "<" -> new BooleanValue(value < otherValue);
                case ">=" -> new BooleanValue(value >= otherValue);
                case "<=" -> new BooleanValue(value <= otherValue);
                case "==" -> new BooleanValue(value == otherValue);
                case "!=" -> new BooleanValue(value != otherValue);
                default -> throw new ExpressionException("Invalid operator");
            };
        }
        throw new ExpressionException("Invalid operand");
    }

    @Override
    public IType getType() {
        return new IntegerType();
    }
}
