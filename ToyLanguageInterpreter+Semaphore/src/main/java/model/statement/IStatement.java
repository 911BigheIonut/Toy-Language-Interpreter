package model.statement;


import exceptions.MyException;
import model.programState.ProgramState;
import model.type.Type;
import model.utils.MyIDictionary;

public interface IStatement {
        ProgramState execute(ProgramState state) throws MyException;
        MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
        IStatement deepCopy();


}