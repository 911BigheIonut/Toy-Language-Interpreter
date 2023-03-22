package model.statement;

import exceptions.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.BoolType;
import model.type.Type;
import model.utils.MyIDictionary;

public class ConditionalAssignmentStatement implements IStatement {

    private final String v;
    private final IExpression exp1;
    private final IExpression exp2;
    private final IExpression exp3;

    public ConditionalAssignmentStatement(String v, IExpression e1, IExpression e2, IExpression e3) {
        this.v=v; exp1=e1; exp2=e2; exp3=e3;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        IStatement ifStmt = new IfStatement(exp1, new AssignStatement(v, exp2), new AssignStatement(v, exp3));
        state.getExeStack().push(ifStmt);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (!exp1.typeCheck(typeEnv).equals(new BoolType()))
            throw new MyException("Condition must be a boolean expression\n");
        Type tv = typeEnv.lookUp(v);
        Type texp2 = exp2.typeCheck(typeEnv);
        Type texp3 = exp3.typeCheck(typeEnv);

        if(!tv.equals(texp2) || !tv.equals(texp3))
            throw new MyException("The variable and the 2 expressions must have the same type\n");

        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new ConditionalAssignmentStatement(v, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy());
    }


    @Override
    public String toString() {return v+"="+exp1+"?"+exp2+":"+exp3;}

}