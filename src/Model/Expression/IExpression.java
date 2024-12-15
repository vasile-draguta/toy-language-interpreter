package Model.Expression;

import Exceptions.ExpressionException;
import Exceptions.TypeException;
import Model.States.ProgState;
import Model.Types.IType;
import Model.Utils.MyIDictionary;
import Model.Values.IValue;

public interface IExpression {
    IValue evaluate(ProgState state);
    String toString();
    IExpression deepCopy();
    IType typeCheck(MyIDictionary<String, IType> typeEnv) throws TypeException;
}
