package com.sinangenc.antlr4generatedfiles;// Generated from PslGrammar.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PslGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PslGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(PslGrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(PslGrammarParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(PslGrammarParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(PslGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#exprStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(PslGrammarParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(PslGrammarParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#printStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(PslGrammarParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(PslGrammarParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(PslGrammarParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(PslGrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierAssignment}
	 * labeled alternative in {@link PslGrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierAssignment(PslGrammarParser.IdentifierAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValueAssignment}
	 * labeled alternative in {@link PslGrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueAssignment(PslGrammarParser.ValueAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#logic_or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_or(PslGrammarParser.Logic_orContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#logic_and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_and(PslGrammarParser.Logic_andContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(PslGrammarParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(PslGrammarParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(PslGrammarParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(PslGrammarParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PslGrammarParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(PslGrammarParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Primary_True}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_True(PslGrammarParser.Primary_TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Primary_False}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_False(PslGrammarParser.Primary_FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Primary_Number}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_Number(PslGrammarParser.Primary_NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Primary_String}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_String(PslGrammarParser.Primary_StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Primary_Parentheses}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_Parentheses(PslGrammarParser.Primary_ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Primary_Identifier}
	 * labeled alternative in {@link PslGrammarParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_Identifier(PslGrammarParser.Primary_IdentifierContext ctx);
}