package com.sinangenc.antlr4generatedfiles;// Generated from PslGrammar.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PslGrammarParser}.
 */
public interface PslGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PslGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PslGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(PslGrammarParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(PslGrammarParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(PslGrammarParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(PslGrammarParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(PslGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(PslGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(PslGrammarParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(PslGrammarParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(PslGrammarParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(PslGrammarParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(PslGrammarParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(PslGrammarParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(PslGrammarParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(PslGrammarParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(PslGrammarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(PslGrammarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PslGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PslGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierAssignment}
	 * labeled alternative in {@link PslGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierAssignment(PslGrammarParser.IdentifierAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierAssignment}
	 * labeled alternative in {@link PslGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierAssignment(PslGrammarParser.IdentifierAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValueAssignment}
	 * labeled alternative in {@link PslGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterValueAssignment(PslGrammarParser.ValueAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValueAssignment}
	 * labeled alternative in {@link PslGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitValueAssignment(PslGrammarParser.ValueAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#logic_or}.
	 * @param ctx the parse tree
	 */
	void enterLogic_or(PslGrammarParser.Logic_orContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#logic_or}.
	 * @param ctx the parse tree
	 */
	void exitLogic_or(PslGrammarParser.Logic_orContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#logic_and}.
	 * @param ctx the parse tree
	 */
	void enterLogic_and(PslGrammarParser.Logic_andContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#logic_and}.
	 * @param ctx the parse tree
	 */
	void exitLogic_and(PslGrammarParser.Logic_andContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#equality}.
	 * @param ctx the parse tree
	 */
	void enterEquality(PslGrammarParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#equality}.
	 * @param ctx the parse tree
	 */
	void exitEquality(PslGrammarParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(PslGrammarParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(PslGrammarParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(PslGrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(PslGrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(PslGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(PslGrammarParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PslGrammarParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(PslGrammarParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PslGrammarParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(PslGrammarParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Primary_True}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_True(PslGrammarParser.Primary_TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Primary_True}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_True(PslGrammarParser.Primary_TrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Primary_False}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_False(PslGrammarParser.Primary_FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Primary_False}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_False(PslGrammarParser.Primary_FalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Primary_Number}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_Number(PslGrammarParser.Primary_NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Primary_Number}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_Number(PslGrammarParser.Primary_NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Primary_String}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_String(PslGrammarParser.Primary_StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Primary_String}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_String(PslGrammarParser.Primary_StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Primary_Parentheses}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_Parentheses(PslGrammarParser.Primary_ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Primary_Parentheses}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_Parentheses(PslGrammarParser.Primary_ParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Primary_Identifier}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_Identifier(PslGrammarParser.Primary_IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Primary_Identifier}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_Identifier(PslGrammarParser.Primary_IdentifierContext ctx);
}