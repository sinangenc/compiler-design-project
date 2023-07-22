package com.sinangenc;

import com.sinangenc.interpreter.Interpreter;

/**
 * This class is created to show how our interpreter can be used.
 * To interpret a code, pass it to static analyzeAndRun() method of the Interpreter class.
 */
public class InterpreterTest {
    public static void main(String[] args) {
        String code = """
                    var a = 5;
                    if(a < 6){
                    print a + "3";
                    }
                    """;

        Interpreter.analyzeAndRun(code);
    }
}
