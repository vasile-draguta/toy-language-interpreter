package Model.Values;

import Exceptions.ExpressionException;
import Model.Types.IType;
import Model.Types.RefType;

public class RefValue implements IValue {
    private final int address;
    private final IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public IType getLocationType() {
        return locationType;
    }


    @Override
    public IValue compose(IValue other, String operator) throws ExpressionException {
        throw new ExpressionException("Cannot compose RefValue with other values");
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(address, locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType.toString() + ")";
    }
}
