package Model.Expression;

import Controller.AppException;
import Model.States.ProgState;
import Model.Values.IValue;

public interface IExpression {
    IValue evaluate(ProgState state) throws AppException;
    String toString();
    IExpression deepCopy();
}
