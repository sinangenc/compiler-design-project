package com.sinangenc.visitor;

import com.sinangenc.antlr4generatedfiles.PslGrammarBaseVisitor;
import com.sinangenc.antlr4generatedfiles.PslGrammarParser;
import com.sinangenc.memory.Memory;
import com.sinangenc.utils.Converter;

/**
 * This class extends the PslGrammarBaseVisitor class which is created by ANTLR and implements a visitor pattern
 * for traversing and evaluating an abstract syntax tree (AST) generated by the PslGrammarParser. It contains methods
 * for visiting different types of nodes in the AST and performing corresponding actions.
 *
 * The visitor uses a Memory object to store and manage variables and their values during the program execution.
 */
public class PslTreeVisitor extends PslGrammarBaseVisitor<Object> {
    private Memory<Object> memory = new Memory<>();

    /**
     * Visits the 'program' rule in the AST and executes all declarations within the 'program'.
     */
    @Override
    public Object visitProgram(PslGrammarParser.ProgramContext ctx) {
        //memory.newScope();

        var declarationList = ctx.declaration();
        for (PslGrammarParser.DeclarationContext currentDeclaration : declarationList){
            visit(currentDeclaration);
        }

        //memory.deleteScope();

        return null;
    }

    /**
     * Visits the 'block' rule in the AST and calls the visit method for each declaration within the 'block'.
     */
    @Override
    public Object visitBlock(PslGrammarParser.BlockContext ctx) {
        memory.newScope();

        var declarationList = ctx.declaration();
        for (PslGrammarParser.DeclarationContext currentDeclaration : declarationList){
            visit(currentDeclaration);
        }

        memory.deleteScope();

        return null;
    }

    /**
     * Visits the 'declaration' rule in the AST and calls the appropriate visit method to execute it.
     */
    @Override
    public Object visitDeclaration(PslGrammarParser.DeclarationContext ctx) {
        visit(ctx.children.get(0));
        return null;
    }

    /**
     * Visits the 'varDecl' rule in the AST and defines a new variable. If an optional initial value exists, assigns it
     * to the variable.
     */
    @Override
    public Object visitVarDecl(PslGrammarParser.VarDeclContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        memory.define(identifier);

        // Check whether the initial value exists
        if(ctx.expression() != null){
            var value = visit(ctx.expression());
            memory.assign(identifier, value);
        }


        return null;
    }

    /**
     * Visits the 'statement' rule in the AST and calls the appropriate visit method to execute it.
     */
    @Override
    public Object visitStatement(PslGrammarParser.StatementContext ctx) {
        return visit(ctx.getChild(0));
    }

    /**
     * Visits the 'exprStmt' rule in the AST and calls the appropriate visit method to execute it.
     */
    @Override
    public Object visitExprStmt(PslGrammarParser.ExprStmtContext ctx) {
        return visit(ctx.expression());
    }

    /**
     * Visits the 'ifStmt' rule in the AST and executes the if or else block based on the condition.
     */
    @Override
    public Object visitIfStmt(PslGrammarParser.IfStmtContext ctx) {
        Object ifCondition = visit(ctx.expression());
        Boolean condition = Converter.getBoolean(ifCondition);

        if (condition){
            return visit(ctx.statement(0));
        }

        // If condition is false and else-block exists...
        if(ctx.statement().size() == 2){
            return visit(ctx.statement(1));
        }

        return null;
    }

    /**
     * Visits the 'printStmt' rule in the AST and prints the result of the visited expression.
     */
    @Override
    public Object visitPrintStmt(PslGrammarParser.PrintStmtContext ctx) {
        var result = visit(ctx.expression());
        String printText = Converter.getString(result);

        System.out.println(printText);
        return null;
    }

    /**
     * Visits the 'whileStmt' rule in the AST and executes the expression until the loop condition becomes false.
     */
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

    /**
     * Visits the 'expression' rule in the AST and evaluates the expression by visiting the corresponding assignment.
     */
    @Override
    public Object visitExpression(PslGrammarParser.ExpressionContext ctx) {
        return visit(ctx.assignment());
    }

    /**
     * Visits the '#IdentifierAssignment' sub-rule in the AST and assigns the result of the corresponding assignment
     * to an identifier in memory.
     */
    @Override
    public Object visitIdentifierAssignment(PslGrammarParser.IdentifierAssignmentContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Object result = visit(ctx.assignment());
        memory.assign(identifier, result);

        return Boolean.TRUE;
    }

    /**
     * Visits the '#ValueAssignment' sub-rule in the AST and assigns the result of the corresponding assignment
     * to an identifier in memory.
     */
    @Override
    public Object visitValueAssignment(PslGrammarParser.ValueAssignmentContext ctx) {
        return visit(ctx.logic_or());
    }

    /**
     * Visits the 'logic_or' rule in the AST and (if necessary) performs the LOGICAL_OR operation.
     */
    @Override
    public Object visitLogic_or(PslGrammarParser.Logic_orContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "logical_and" directly. no need for LOGICAL_OR
            return visit(ctx.logic_and(0));
        }
        else { // Check, whether all operands are Boolean, and perform LOGICAL_OR operation.
            Boolean result = false;
            for (var eq : ctx.logic_and()){
                Object tmpResult = visit(eq);
                Boolean condition = Converter.getBoolean(tmpResult);

                result = result || condition;
            }

            return result;
        }
    }

    /**
     * Visits the 'logic_and' rule in the AST and (if necessary) performs the LOGICAL_AND operation.
     */
    @Override
    public Object visitLogic_and(PslGrammarParser.Logic_andContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "equality" directly. no need for LOGICAL_AND
            return visit(ctx.equality(0));
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

    /**
     * Visits the 'equality' rule in the AST and (if necessary) performs the '!=' and '==' operations.
     */
    @Override
    public Object visitEquality(PslGrammarParser.EqualityContext ctx) {
        Object operand = visit(ctx.comparison(0));

        if(ctx.getChildCount() == 1){ // Use the result of "comparison" directly. no need for further processing
            return operand;
        }

        Object operand2 = visit(ctx.comparison(1));

        Boolean result = switch (ctx.getChild(1).getText()){
            case "==" -> operand.equals(operand2);
            case "!=" -> !operand.equals(operand2);
            default -> throw new UnsupportedOperationException("Unexpected value: " + ctx.getChild(1).getText()); // will be never used
        };

        return result;
    }

    /**
     * Visits the 'comparison' rule in the AST and (if necessary) performs the ('>' , '>=', '<', '<=') operations.
     */
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
            default -> throw new UnsupportedOperationException("Unexpected operator: " + ctx.getChild(1).getText()); // will be never used
        };

        return result;
    }

    /**
     * Visits the 'term' rule in the AST and (if necessary) performs the '-' and '+' operations. While '-' operatio can
     * only be applied on numbers(arithmetic subtraction), '+' operation can be applied on numbers(arithmetic addition)
     * and Strings(concatenation).
     */
    @Override
    public Object visitTerm(PslGrammarParser.TermContext ctx) {
        Object result = visit(ctx.factor(0));
        if(ctx.getChildCount() == 1){ // Use the result of "factor" directly. no need for further processing
            return result;
        }
        else {
            // in the even positions (0,2,4..) there is always a 'factor' and
            // in the odd positions there is always a 'minus or plus'
            for (int i = 1; i < ctx.getChildCount(); i+=2){
                Object operand = visit(ctx.getChild(i+1));

                if (ctx.getChild(i).getText().equals("+")) { // '+' Operation
                    if (result instanceof Double && operand instanceof Double){ // Arithmetic addition
                        result = Converter.getDouble(result) + Converter.getDouble(operand);
                    }
                    else if(result instanceof String || operand instanceof String){ // String concatenation
                        result = Converter.getString(result) + Converter.getString(operand);
                    }
                    else{ // Incompatible operand type
                        throw new UnsupportedOperationException("In addition operation, either both parameters must " +
                                "be NUMBER or at least one of the parameters must be of type STRING.");
                    }
                }
                else { // Arithmetic subtraction '-' Operation
                    result = Converter.getDouble(result) - Converter.getDouble(operand);
                }

            }

            return result;
        }
    }

    /**
     * Visits the 'factor' rule in the AST and (if necessary) performs the '/' and '*' operations for Double values.
     */
    @Override
    public Object visitFactor(PslGrammarParser.FactorContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "unary" directly. no need for further processing
            Object result = visit(ctx.unary(0));
            return result;
        }
        else {
            Object tmpResult = visit(ctx.unary(0));
            Double result = Converter.getDouble(tmpResult);

            // even positions (0,2,4..) always a 'factor'
            // odd positions always a 'minus or plus'
            for (int i = 1; i < ctx.getChildCount(); i+=2){
                Object tmpOperand = visit(ctx.getChild(i+1));
                Double operand = Converter.getDouble(tmpOperand);

                if (ctx.getChild(i).getText().equals("/")) { // (/) Operation
                    result /= operand;
                }
                else { // (*) Operation
                    result *= operand;
                }

            }

            return result;
        }
    }

    /**
     * Visits the 'unary' rule in the AST and (if necessary) performs the '!' operation for Boolean values and
     * '-' operation for Double values.
     */
    @Override
    public Object visitUnary(PslGrammarParser.UnaryContext ctx) {
        if(ctx.getChildCount() == 1){ // Use the result of "primary" directly. no need for further processing
            return visit(ctx.primary());
        }
        else {
            Object unaryValue = visit(ctx.unary());

            if (ctx.NOT() != null) { // NOT (!) operation
                Boolean result = !Converter.getBoolean(unaryValue);
                return result;
            } else { // MINUS (-) operation
                Double result = - Converter.getDouble(unaryValue);
                return result;
            }
        }
    }

    /**
     * Visits the '#Primary_True' sub-rule in the AST and returns the Boolean value TRUE.
     */
    @Override
    public Object visitPrimary_True(PslGrammarParser.Primary_TrueContext ctx) {
        return Boolean.TRUE;
    }

    /**
     * Visits the '#Primary_False' sub-rule in the AST and returns the Boolean value FALSE.
     */
    @Override
    public Object visitPrimary_False(PslGrammarParser.Primary_FalseContext ctx) {
        return Boolean.FALSE;
    }

    /**
     * Visits the '#Primary_Number' sub-rule in the AST and returns the numeric value as a Double.
     */
    @Override
    public Object visitPrimary_Number(PslGrammarParser.Primary_NumberContext ctx) {
        return Double.valueOf(ctx.getText());
    }

    /**
     * Visits the '#Primary_String' sub-rule in the AST and returns the String value without quotes.
     */
    @Override
    public Object visitPrimary_String(PslGrammarParser.Primary_StringContext ctx) {
        String textWithQuotes = ctx.getText();
        return textWithQuotes.substring(1, textWithQuotes.length()-1);
    }

    /**
     * Visits the '#Primary_Parentheses' sub-rule in the AST and evaluates the expression within the parentheses.
     */
    @Override
    public Object visitPrimary_Parentheses(PslGrammarParser.Primary_ParenthesesContext ctx) {
        return visit(ctx.expression());
    }

    /**
     * Visits the '#Primary_Identifier' sub-rule in the AST and retrieves the value of the variable from memory.
     * For defined but not yet assigned variables it throws a runtime exception.(due to semantic rules)
     */
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