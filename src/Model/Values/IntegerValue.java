package Model.Values;

import Controller.AppException;
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
    public IValue compose(IValue other, String operator) throws AppException {
        if (other instanceof IntegerValue) {
            int otherValue = ((IntegerValue) other).getValue();
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
                default -> throw new AppException("Invalid operator");
            };
        }
        throw new AppException("Invalid operand");
    }

    @Override
    public IType getType() {
        return new IntegerType();
    }
}
