package Model.Values;

import Controller.AppException;
import Model.Types.IType;

public interface IValue {
    public IValue compose(IValue other, String operator) throws AppException;
    IType getType();
    public boolean equals(Object other);
    public IValue deepCopy();
    public String toString();
}
