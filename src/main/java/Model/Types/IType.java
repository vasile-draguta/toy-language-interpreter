package Model.Types;

import Exceptions.ExpressionException;
import Model.Values.IValue;

public interface IType {
    IValue defaultValue();
    IType compose(String operation) throws ExpressionException;
    boolean equals(IType other);
    String toString();
    IType deepCopy();
}
