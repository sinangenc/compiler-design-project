package com.sinangenc.memory;

/**
 * This interface describes the required methods to implement a memory structure which is used to store variables.
 */
public interface MemoryInterface<T> {
    public void newScope();
    public void deleteScope();
    public void define(String variableName);
    public void assign(String variableName, T value);
    public T get(String variableName);
}
