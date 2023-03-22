package model.statement;

import exceptions.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.BoolType;
import model.type.Type;
import model.utils.MyIDictionary;

public class DoWhileStatement implements IStatement{
    private final IStatement statement;
    private final IExpression expression;

    public DoWhileStatement(IExpression expression, IStatement statement) {
        this.statement = statement;
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStatement converted = new CompoundStatement(statement, new WhileStatement(expression, statement));
        state.getExeStack().push(converted);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExpression = expression.typeCheck(typeEnv);
        if (typeExpression.equals(new BoolType())) {
            statement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else
            throw new MyException("Condition in the do while statement must be bool!");
    }

    @Override
    public IStatement deepCopy() {
        return new DoWhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("do {%s} while (%s)", statement, expression);
    }
}