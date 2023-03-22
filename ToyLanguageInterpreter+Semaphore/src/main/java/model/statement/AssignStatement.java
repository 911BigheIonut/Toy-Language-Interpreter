package model.statement;

import exceptions.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.Type;
import model.utils.MyIDictionary;
import model.value.Value;

public class AssignStatement implements IStatement {
    private final String key;
    private final IExpression expression;

    public AssignStatement(String key, IExpression expression) {
        this.key = key;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symbolTable = state.getSymTable();

        if (symbolTable.isDefined(key)) {
            Value value = expression.eval(symbolTable, state.getHeap());
            Type typeId = (symbolTable.lookUp(key)).getType();
            if (value.getType().equals(typeId)) {
                symbolTable.update(key, value);
            } else {
                throw new MyException("Declared type of variable " + key + " and type of the assigned expression do not match.");
            }
        } else {
            throw new MyException("The used variable " + key + " was not declared before.");
        }
        state.setSymTable(symbolTable);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(key);
        Type typeExpr = expression.typeCheck(typeEnv);
        if (typeVar.equals(typeExpr))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types.");
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(key, expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("%s = %s", key, expression.toString());
    }
}