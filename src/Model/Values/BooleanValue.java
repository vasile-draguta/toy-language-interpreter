package Model.Values;

import Controller.AppException;
import Model.Types.IType;
import Model.Types.BooleanType;

public class BooleanValue implements IValue {
    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object other) {
        if (other instanceof BooleanValue) {
            return ((BooleanValue) other).getValue() == value;
        }
        return false;
    }

    @Override
    public IValue deepCopy() {
        return new BooleanValue(value);
    }

    @Override
    public IType getType() {
        return new BooleanType();
    }

    public IValue compose(IValue other, String operator) throws AppException {
        if (other instanceof BooleanValue) {
            boolean otherValue = ((BooleanValue) other).getValue();
            return switch (operator) {
                case "&&" -> new BooleanValue(value && otherValue);
                case "||" -> new BooleanValue(value || otherValue);
                case "==" -> new BooleanValue(value == otherValue);
                case "!=" -> new BooleanValue(value != otherValue);
                default -> throw new IllegalStateException("Unexpected value: " + operator);
            };
        }
        throw new AppException("Invalid operation between boolean and " + other.getType());
    }
}
