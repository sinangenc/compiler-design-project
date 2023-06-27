package com.sinangenc.visitor;

import com.sinangenc.antlr4generatedfiles.PslGrammarBaseVisitor;
import com.sinangenc.antlr4generatedfiles.PslGrammarParser;
import com.sinangenc.memory.Memory;
import com.sinangenc.utils.Converter;

public class PslTreeVisitor extends PslGrammarBaseVisitor<Object> {
    private Memory<Object> memory = new Memory<>();
    @Override
    public Object visitProgram(PslGrammarParser.ProgramContext ctx) {
        //memory.newScope();

        var list = ctx.declaration();
        for (int i = 0; i < list.size(); i++)
            visit(list.get(i));

        //memory.deleteScope();

        return null;
    }

    @Override
    public Object visitBlock(PslGrammarParser.BlockContext ctx) {
        memory.newScope();

        var list = ctx.declaration();
        for (int i = 0; i < list.size(); i++)
            visit(list.get(i));

        memory.deleteScope();

        return null;
    }

    @Override
    public Object visitDeclaration(PslGrammarParser.DeclarationContext ctx) {
        visit(ctx.children.get(0));
        return null;
    }

    @Override
    public Object visitVarDecl(PslGrammarParser.VarDeclContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        memory.define(identifier);

        if(ctx.expression() != null){
            var value = visit(ctx.expression());
            memory.assign(identifier, value);
        }


        return null;
    }

    @Override
    public Object visitStatement(PslGrammarParser.StatementContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Object visitExprStmt(PslGrammarParser.ExprStmtContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitIfStmt(PslGrammarParser.IfStmtContext ctx) {
        Object ifCondition = visit(ctx.expression());
        Boolean condition = Converter.getBoolean(ifCondition);

        if (condition){
            return visit(ctx.statement(0));
        }

        // If else-block exists...
        if(ctx.statement().size() == 2){
            return visit(ctx.statement(1));
        }

        return null;
    }

    @Override
    public Object visitPrintStmt(PslGrammarParser.PrintStmtContext ctx) {
        var result = visit(ctx.expression());
        String printText = Converter.getString(result);

        System.out.println(printText);
        return null;
    }

    @Override
    public Object visitWhileStmt(PslGrammarParser.WhileStmtContext ctx) {

        while (true){
            Object loopCondition = visit(ctx.expression());
            Boolean condition = Converter.getBoolean(loopCondition);

            if(!condition){
                break;
            }

            visit(ctx.statement());
        }

        return null;
    }

    @Override
    public Object visitExpression(PslGrammarParser.ExpressionContext ctx) {
        return visit(ctx.assignment());
    }

    @Override
    public Object visitIdentifierAssignment(PslGrammarParser.IdentifierAssignmentContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Object result = visit(ctx.assignment());
        memory.assign(identifier, result);

        return Boolean.TRUE;
    }

    @Override
    public Object visitValueAssignment(PslGrammarParser.ValueAssignmentContext ctx) {
        var result = visit(ctx.logic_or());
        return result;
    }

    @Override
    public Object visitLogic_or(PslGrammarParser.Logic_orContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "logical_and" directly. no need for further processing
            Object result = visit(ctx.logic_and(0));
            return result;
        }
        else { // Check, whether all operands are Boolean, and perform LOGICAL_AND operation.
            Boolean result = false;
            for (var eq : ctx.logic_and()){
                Object tmpResult = visit(eq);
                Boolean condition = Converter.getBoolean(tmpResult);

                result = result || condition;
            }

            return result;
        }
    }

    @Override
    public Object visitLogic_and(PslGrammarParser.Logic_andContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "equality" directly. no need for further processing
            Object result = visit(ctx.equality(0));
            return result;
        }
        else { // Check, whether all operands are Boolean, and perform LOGICAL_AND operation.
            Boolean result = true;
            for (var eq : ctx.equality()){
                Object tmpResult = visit(eq);
                Boolean condition = Converter.getBoolean(tmpResult);

                result = result && condition;
            }

            return result;
        }
    }

    @Override
    public Object visitEquality(PslGrammarParser.EqualityContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "comparison" directly. no need for further processing
            Object tmpOperand = visit(ctx.comparison(0));
            return tmpOperand;
        }

        Object operand = visit(ctx.comparison(0));
        Object operand2 = visit(ctx.comparison(1));

        Boolean result = switch (ctx.getChild(1).getText()){
            case "==" -> operand.equals(operand2);
            case "!=" -> !operand.equals(operand2);
            default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(1).getText());
        };

        return result;
    }

    @Override
    public Object visitComparison(PslGrammarParser.ComparisonContext ctx) {
        Object tmpOperand = visit(ctx.term(0));
        if(ctx.getChildCount() == 1){ // Use the result of "term" directly. no need for further processing
            return tmpOperand;
        }

        Object tmpOperand2 = visit(ctx.getChild(2));
        Double operand = Converter.getDouble(tmpOperand);
        Double operand2 = Converter.getDouble(tmpOperand2);

        Boolean result = switch (ctx.getChild(1).getText()){
            case ">" -> operand > operand2;
            case ">=" -> operand >= operand2;
            case "<" -> operand < operand2;
            case "<=" -> operand <= operand2;
            default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(1).getText());
        };

        return result;
    }

    @Override
    public Object visitTerm(PslGrammarParser.TermContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "factor" directly. no need for further processing
            Object result = visit(ctx.factor(0));
            return result;
        }
        else {
            Object result = visit(ctx.factor(0));
            //Object tmpResult = visit(ctx.factor(0));
            //Double result = Converter.getDouble(tmpResult);

            for (int i = 1; i < ctx.getChildCount(); i+=2){
                Object operand = visit(ctx.getChild(i+1));

                if (ctx.getChild(i).getText().equals("+")) {
                    if (result instanceof Double && operand instanceof Double){ // Arithmetic addition
                        result = Converter.getDouble(result) + Converter.getDouble(operand);
                    }
                    else if(result instanceof String || operand instanceof String){ // String concatenation
                        result = Converter.getString(result) + Converter.getString(operand);
                    }
                    else{
                        throw new RuntimeException("En az biri string olmali yada double + double");
                    }
                }
                else { // equals("-")
                    result = Converter.getDouble(result) - Converter.getDouble(operand);
                }

            }

            return result;
        }
    }

    @Override
    public Object visitFactor(PslGrammarParser.FactorContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "unary" directly. no need for further processing
            Object result = visit(ctx.unary(0));
            return result;
        }
        else {
            Object tmpResult = visit(ctx.unary(0));
            Double result = Converter.getDouble(tmpResult);

            for (int i = 1; i < ctx.getChildCount(); i+=2){
                Object tmpOperand = visit(ctx.getChild(i+1));
                Double operand = Converter.getDouble(tmpOperand);

                if (ctx.getChild(i).getText().equals("/")) {
                    result /= operand;
                }
                else { // equals("*")
                    result *= operand;
                }

            }

            return result;
        }
    }

    @Override
    public Object visitUnary(PslGrammarParser.UnaryContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "primary" directly. no need for further processing
            Object result = visit(ctx.primary());
            return result;
        }
        else {
            Object unaryValue = visit(ctx.unary());

            if (ctx.NOT() != null) {
                // Code for handling the NOT case

                Boolean result = !Converter.getBoolean(unaryValue);

                return result;
            } else { //  (ctx.MINUS() != null)
                // Code for handling the MINUS case

                Double result = - Converter.getDouble(unaryValue);

                return result;
            }


            //throw new UnsupportedOperationException("Not yet implemented!!! " + result);
        }
    }

    @Override
    public Object visitPrimary_True(PslGrammarParser.Primary_TrueContext ctx) {
        return Boolean.TRUE;
    }

    @Override
    public Object visitPrimary_False(PslGrammarParser.Primary_FalseContext ctx) {
        return Boolean.FALSE;
    }

    @Override
    public Object visitPrimary_Number(PslGrammarParser.Primary_NumberContext ctx) {
        return Double.valueOf(ctx.getText());
    }

    @Override
    public Object visitPrimary_String(PslGrammarParser.Primary_StringContext ctx) {
        String textWithQuotes = ctx.getText();
        return textWithQuotes.substring(1, textWithQuotes.length()-1);
    }

    @Override
    public Object visitPrimary_Parentheses(PslGrammarParser.Primary_ParenthesesContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitPrimary_Identifier(PslGrammarParser.Primary_IdentifierContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Object value = memory.get(identifier);

        if (value == null){
            throw new NullPointerException(String.format("Variable<%s> is null!", identifier));
        }

        return value;
    }

}