package model.statement;

import exceptions.MyException;
import model.programState.ProgramState;
import model.type.Type;
import model.utils.MyIDictionary;
import model.utils.MyIStack;

public class SleepStatement implements IStatement{
    private final int value;

    public SleepStatement(int value) {
        this.value = value;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if (value != 0) {
            MyIStack<IStatement> exeStack = state.getExeStack();
            exeStack.push(new SleepStatement(value - 1));
            state.setExeStack(exeStack);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new SleepStatement(value);
    }

    @Override
    public String toString() {
        return String.format("sleep(%s)", value);
    }
}