package Model.Types;

import Exceptions.ExpressionException;
import Model.Values.IValue;
import Model.Values.IntegerValue;

public class IntegerType implements IType {

    public IValue defaultValue() {
        return new IntegerValue(0);
    }

    public boolean equals(IType other) {
        return other instanceof IntegerType;
    }

    public String toString() {
        return "Integer";
    }

    public IType compose(String operation) throws ExpressionException {
        return switch (operation) {
            case "+", "-", "*", "/", "<", "<=", "==", "!=", ">", ">=" -> new IntegerType();
            default -> throw new ExpressionException("Invalid operation for IntegerType");
        };
    }

    @Override
    public IType deepCopy() {
        return new IntegerType();
    }

}
