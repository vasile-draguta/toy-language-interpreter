package Model.Values;

import Exceptions.ExpressionException;
import Model.Types.IType;

public interface IValue {
    IValue compose(IValue other, String operator) throws ExpressionException;
    IType getType();
    boolean equals(Object other);
    IValue deepCopy();
    String toString();
}
