package com.sinangenc.memory;

public interface MemoryInterface<T> {
    public void newScope();
    public void deleteScope();
    public void define(String variableName);
    public void assign(String variableName, T value);
    public T get(String variableName);
}
