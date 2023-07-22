package com.sinangenc;

import com.sinangenc.interpreter.Interpreter;

/**
 * This class is created to show how our interpreter can be used.
 * To interpret a code, pass it to static analyzeAndRun() method of the Interpreter class.
 */
public class InterpreterTest {
    public static void main(String[] args) {
        String code = """
                var global = 100;
                var i = 0;
                i = i + 1;
                if (i == 1){
                	print "Basic assignment: ok";
                }
                                    
                i = 0;
                while (i < 10){
                	i = i + 1;
                }
                if (i == 10){
                	print "Basic looping: ok";
                }

                var log1 = false and false;
                var log2 = false and true;
                var log3 = true and false;
                var log4 = true and true;
                                    
                if (!log1 and !log2 and !log3 and log4){
                 	print "Logic and: ok";
                }
                                    
                log1 = false or false;
                log2 = false or true;
                log3 = true or false;
                log4 = true or true;
                                    
                if (!log1 and log2 and log3 and log4){
                 	print "Logic or: ok";
                }
                """;

        Interpreter.analyzeAndRun(code);
    }
}
