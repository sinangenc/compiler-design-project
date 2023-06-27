package com.sinangenc.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memory<T> implements MemoryInterface<T> {
    private final List<Map<String, T>> memory = new ArrayList<>();
    private int scopeLevel;

    public Memory() {
        memory.add(new HashMap<>());
        scopeLevel = 0;
    }

    @Override
    public void newScope(){
        scopeLevel++;
        memory.add(new HashMap<>());
    }

    @Override
    public void deleteScope(){
        memory.remove(scopeLevel);
        scopeLevel--;
    }

    @Override
    public void define(String variableName){
        if (check(variableName)){
            throw new IllegalArgumentException(
                    String.format("Variable<%s> has already defined!", variableName)
            );
        }

        memory.get(scopeLevel).put(variableName, null);
    }

    @Override
    public void assign(String variableName, T value){
        if (!check(variableName)){
            throw new IllegalArgumentException(
                    String.format("Variable<%s> has not defined!", variableName)
            );
        }

        int scope = findScope(variableName);
        memory.get(scope).replace(variableName, value);
    }

    @Override
    public T get(String variableName){
        if (!check(variableName)){
            throw new IllegalArgumentException(
                    String.format("Variable<%s> has not defined!", variableName)
            );
        }

        int scope = findScope(variableName);

        return memory.get(scope).get(variableName);
    }


    private int findScope(String variableName){
        for (int i = scopeLevel; i >= 0; i--){
            if (checkVariableByScope(i, variableName)){
                return i;
            }
        }

        throw new IllegalArgumentException(
                String.format("Variable<%s> has not defined!", variableName)
        );
    }
    private boolean check(String variableName){
        for (int i = scopeLevel; i >= 0; i--){
            if (checkVariableByScope(i, variableName)){
                return true;
            }
        }

        return false;
    }
    private boolean checkVariableByScope(int scopeLevel, String variableName){
        return memory.get(scopeLevel).containsKey(variableName);
    }
}