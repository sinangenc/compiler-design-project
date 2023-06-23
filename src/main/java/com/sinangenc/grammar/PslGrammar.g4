grammar PslGrammar;

/*
 * Parser Rules
 */
 program
     :   declaration*
     ;


declaration
     :   varDecl
     |   statement
     ;


varDecl
     :   VAR IDENTIFIER (ASSIGN_SIGN expression)? SEMICOLON
     ;


statement
     :   exprStmt
     |   ifStmt
     |   printStmt
     |   whileStmt
     |   block
     ;


exprStmt
     :   expression SEMICOLON
     ;


ifStmt
     :   IF LPAREN expression RPAREN statement (ELSE statement)?
     ;


printStmt
     :   PRINT expression SEMICOLON
     ;


whileStmt
     :   WHILE LPAREN expression RPAREN statement
     ;


block
     :   LPAREN_CURLY declaration* RPAREN_CURLY
     ;


expression
     :   assignment
     ;


assignment
     :   IDENTIFIER ASSIGN_SIGN assignment  #IdentifierAssignment
     |   logic_or                           #ValueAssignment
     ;


logic_or
     :   logic_and (OR logic_and)*
     ;


logic_and
     :   equality (AND equality)*
     ;


equality
     :   comparison ((NOT_EQUAL | EQUAL) comparison)*
     ;


comparison
     :   term ((GREATER_THAN | GREATER_THAN_OR_EQUAL | LESS_THAN | LESS_THAN_OR_EQUAL) term)*
     ;


term
     :   factor ((MINUS | PLUS) factor)*
     ;


factor
     :   unary ((SLASH | STAR) unary)*
     ;


unary
     :   (NOT | MINUS) unary
     |   primary
     ;


primary
     :   TRUE                           #Primary_True
     |   FALSE                          #Primary_False
     |   NUMBER                         #Primary_Number
     |   STRING                         #Primary_String
     |   LPAREN expression RPAREN       #Primary_Parentheses
     |   IDENTIFIER                     #Primary_Identifier
     ;



/*
 * Lexer Rules
 */

// Keywords
TRUE    : 'true' ;
FALSE   : 'false' ;
AND     : 'and' ;
OR      : 'or' ;
PRINT   : 'print' ;
IF      : 'if' ;
ELSE    : 'else' ;
WHILE   : 'while' ;
VAR     : 'var' ;

// Special Characters
SEMICOLON       : ';' ;
LPAREN          : '(' ;
RPAREN          : ')' ;
LPAREN_CURLY    : '{' ;
RPAREN_CURLY    : '}' ;

// Operators
PLUS                    : '+' ;
MINUS                   : '-' ;
STAR                    : '*' ;
SLASH                   : '/' ;
EQUAL                   : '==' ;
ASSIGN_SIGN             : '=' ;
NOT_EQUAL               : '!=' ;
NOT                     : '!' ;
GREATER_THAN_OR_EQUAL   : '>=' ;
GREATER_THAN            : '>' ;
LESS_THAN_OR_EQUAL      : '<=' ;
LESS_THAN               : '<' ;


// String & Number...
STRING      : '"' ~["]* '"' ;
NUMBER      : [0-9]+ ('.' [0-9]+)? ;
IDENTIFIER  : [A-Za-z][A-Za-z0-9]* ;

// Comments and Whitespace
COMMENT     : '//' .*? '\n' -> skip ;
WHITESPACE  : [ \t\r\n]+ -> skip ;