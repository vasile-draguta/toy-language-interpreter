package Model.Values;

import Exceptions.ExpressionException;
import Model.Types.IType;

public interface IValue {
    public IValue compose(IValue other, String operator) throws ExpressionException;
    IType getType();
    public boolean equals(Object other);
    public IValue deepCopy();
    public String toString();
}
