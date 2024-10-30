package Model.Types;

import Exceptions.ExpressionException;
import Model.Values.IValue;

public interface IType {
    public IValue defaultValue();
    public IType compose(String operation) throws ExpressionException;
    public boolean equals(IType other);
    public String toString();
    public IType deepCopy();
}
