package model.expression;

import exceptions.MyException;
import model.type.Type;
import model.utils.MyIDictionary;
import model.utils.MyIHeap;
import model.value.BoolValue;
import model.value.Value;

public class NotExpression implements IExpression{
    private final IExpression expression;

    public NotExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return expression.typeCheck(typeEnv);
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap heap) throws MyException {
        BoolValue value = (BoolValue) expression.eval(table, heap);
        if (!value.getValue())
            return new BoolValue(true);
        else
            return new BoolValue(false);
    }

    @Override
    public IExpression deepCopy() {
        return new NotExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("!(%s)", expression);
    }
}