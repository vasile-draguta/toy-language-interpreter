package Model.Types;

import Model.Values.IValue;
import Model.Values.StringValue;

public class StringType implements IType {

    public StringType() {}

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public boolean equals(IType other) {
        return other instanceof StringType;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public IType deepCopy() {
        return new StringType();
    }

    @Override
    public IType compose(String operation) {
        return switch (operation) {
            case "+" -> new StringType();
            case "==", "!=" -> new BooleanType();
            default -> throw new RuntimeException("Invalid operation for string type");
        };
    }
}
