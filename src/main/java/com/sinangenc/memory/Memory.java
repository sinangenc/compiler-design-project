package com.sinangenc.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides a memory structure to store (variable_name, value) pairs in relation to a scope.
 * It holds scopes in an ArrayList. As a result of this, each scope could have max. 1 direct sub-scope at a particular
 * moment. Although this is a serious restriction, it is sufficient for our semantic rules. The first element of
 * ArrayList represents the base scope in the interpreter and each element is sub-scope of the previous one.
 */
public class Memory<T> implements MemoryInterface<T> {
    private final List<Map<String, T>> memory = new ArrayList<>();
    private int scopeLevel;

    public Memory() {
        memory.add(new HashMap<>());
        scopeLevel = 0;
    }

    /**
     * Creates a new scope. The new scope will be sub-scope of current scope.
     */
    @Override
    public void newScope(){
        scopeLevel++;
        memory.add(new HashMap<>());
    }

    /**
     * Deletes the current scope.
     */
    @Override
    public void deleteScope(){
        memory.remove(scopeLevel);
        scopeLevel--;
    }

    /**
     * Defines a new variable in the current scope.
     * Throws a runtime exception if the variable name has already been defined in the current or higher scopes.
     */
    @Override
    public void define(String variableName){
        if (check(variableName)){
            throw new IllegalArgumentException(
                    String.format("Variable<%s> has already been defined!", variableName)
            );
        }

        memory.get(scopeLevel).put(variableName, null);
    }

    /**
     * Assigns a value to a variable in the current scope or higher scope.
     * Throws a runtime exception if the variable name has not been defined.
     */
    @Override
    public void assign(String variableName, T value){
        if (!check(variableName)){
            throw new IllegalArgumentException(
                    String.format("Variable<%s> has not been defined yet!", variableName)
            );
        }

        int scope = findScope(variableName);
        memory.get(scope).replace(variableName, value);
    }

    /**
     * Retrieves the value of a variable from the memory.
     * Throws a runtime exception if the variable name does not exist.
     */
    @Override
    public T get(String variableName){
        if (!check(variableName)){
            throw new IllegalArgumentException(
                    String.format("Variable<%s> has not been defined yet!", variableName)
            );
        }

        int scope = findScope(variableName);

        return memory.get(scope).get(variableName);
    }

    /**
     * Finds the scope level where the given variable is defined.
     * Throws a runtime exception if the variable is not found.
     */
    private int findScope(String variableName){
        for (int i = scopeLevel; i >= 0; i--){
            if (checkVariableByScope(i, variableName)){
                return i;
            }
        }

        throw new IllegalArgumentException(
                String.format("Variable<%s> has not been defined yet!", variableName)
        );
    }

    /**
     * Returns whether the given variable is defined in any of the scopes.
     */
    private boolean check(String variableName){
        for (int i = scopeLevel; i >= 0; i--){
            if (checkVariableByScope(i, variableName)){
                return true;
            }
        }

        return false;
    }

    /**
     * Returns whether the given variable is defined in a specific scope.
     */
    private boolean checkVariableByScope(int scopeLevel, String variableName){
        return memory.get(scopeLevel).containsKey(variableName);
    }
}