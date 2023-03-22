package model.utils;


import exceptions.MyException;

import java.util.List;

public interface MyIList<T> {
    void add(T elem);
    T pop() throws MyException;
    boolean isEmpty();
    List<T> getList();
}