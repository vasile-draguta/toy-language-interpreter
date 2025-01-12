package Model.Types;

import Exceptions.ExpressionException;
import Model.Values.IValue;
import Model.Values.BooleanValue;

public class BooleanType implements IType {

    public IValue defaultValue() {
        return new BooleanValue(false);
    }

    public String toString() {
        return "Boolean";
    }

    public boolean equals(IType other) {
        return other instanceof BooleanType;
    }

    public IType compose(String operation) throws ExpressionException {
        return switch (operation) {
            case "&&", "||", "==", "!=" -> new BooleanType();
            default -> throw new ExpressionException("Invalid operation for boolean type");
        };
    }

    @Override
    public IType deepCopy() {
        return new BooleanType();
    }
}
