package Model.Types;

import Exceptions.ExpressionException;
import Model.Values.IValue;
import Model.Values.RefValue;

public class RefType implements IType {
    private final IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public boolean equals(IType other) {
        if (other instanceof RefType) {
            return inner.equals(((RefType)other).getInner());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public IType deepCopy() {
        return new RefType(inner.deepCopy());
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public IType compose(String operation) throws ExpressionException {
        throw new ExpressionException("Cannot compose RefType with other types");
    }

}
