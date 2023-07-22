package com.sinangenc.interpreter;

import com.sinangenc.antlr4generatedfiles.PslGrammarLexer;
import com.sinangenc.antlr4generatedfiles.PslGrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * The Interpreter class is responsible for analyzing and interpreting SPL code.
 */
public class Interpreter {
    /**
     * This method uses the PslGrammarLexer and PslGrammarParser to generate an Abstract Syntax Tree (AST) from the provided code.
     * After parsing, it interprets and executes the code by visiting the AST with the PslTreeVisitor.
     * If any exceptions occur, it prints the error message.
     */
    public static void analyzeAndRun(String code){
        try {
            PslGrammarLexer lexer = new PslGrammarLexer(CharStreams.fromString(code));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            PslGrammarParser parser = new PslGrammarParser(tokens);

            // Head node
            ParseTree tree = parser.program();

            PslTreeVisitor treeVisitor = new PslTreeVisitor();
            treeVisitor.visit(tree);

        }catch (IllegalArgumentException |
                NullPointerException e){
            System.err.println(e.getMessage());
        }
    }
}