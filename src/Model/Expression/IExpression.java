package Model.Expression;

import Model.States.ProgState;
import Model.Values.IValue;

public interface IExpression {
    IValue evaluate(ProgState state);
    String toString();
    IExpression deepCopy();
}
