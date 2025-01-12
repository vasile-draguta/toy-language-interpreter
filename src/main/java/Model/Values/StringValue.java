package Model.Values;

import Exceptions.ExpressionException;
import Model.Types.IType;
import Model.Types.StringType;

public class StringValue implements IValue {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public IValue compose(IValue other, String operator) throws ExpressionException {
        switch (operator) {
            case "+":
                if (other instanceof StringValue) {
                    return new StringValue(value + ((StringValue) other).getValue());
                }
                throw new ExpressionException("Invalid operation between string and " + other.getType());
            case "==":
                if (other instanceof StringValue) {
                    return new BooleanValue(value.equals(((StringValue) other).getValue()));
                }
                throw new ExpressionException("Invalid operation between string and " + other.getType());
            case "!=":
                if (other instanceof StringValue) {
                    return new BooleanValue(!value.equals(((StringValue) other).getValue()));
                }
                throw new ExpressionException("Invalid operation between string and " + other.getType());
            default:
                throw new ExpressionException("Invalid operation for string type");
        }
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof StringValue) {
            return ((StringValue) other).getValue().equals(value);
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(value);
    }
}
