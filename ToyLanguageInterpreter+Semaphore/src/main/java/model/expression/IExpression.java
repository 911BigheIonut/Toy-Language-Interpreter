package model.expression;


import exceptions.MyException;
import model.type.Type;
import model.utils.MyIDictionary;
import model.utils.MyIHeap;
import model.value.Value;

public interface IExpression {
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
    Value eval(MyIDictionary<String, Value> table, MyIHeap heap) throws MyException;
    IExpression deepCopy();
}