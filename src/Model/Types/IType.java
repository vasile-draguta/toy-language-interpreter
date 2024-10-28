package Model.Types;

import Controller.AppException;
import Model.Values.IValue;

public interface IType {
    public IValue defaultValue();
    public IType compose(String operation) throws AppException;
    public boolean equals(IType other);
    public String toString();
    public IType deepCopy();
}
