# Semantic Rules

Here are some semantic rules which are implemented to the interpreter:

## 1- Implicit Boolean Conversion

**Q: What happens when non-boolean values are used in places where they are expected?**

**A:** Non-boolean values are not allowed where a boolean value is expected. For non-boolean values type casting is not performed implicitly. In this case a runtime exception is thrown.

**The reason for the decision:** Implicit boolean conversion brings with it many edge cases and error-proneness. E.g. "false" or "0"

**How to check:** Dynamically. Additionally, 2 grammar rules have been changed as follows:

```
equality
//:   comparison ((NOT_EQUAL | EQUAL) comparison)*
  :   comparison ((NOT_EQUAL | EQUAL) comparison)?
;


comparison
//:   term ((GREATER_THAN | GREATER_THAN_OR_EQUAL | LESS_THAN | LESS_THAN_OR_EQUAL) term)*
  :   term ((GREATER_THAN | GREATER_THAN_OR_EQUAL | LESS_THAN | LESS_THAN_OR_EQUAL) term)?
;
```


## 2- Operator Overloading

**Q: Can arithmetic operators also be used for values that are not numbers?**

**A:** It is possible to use some operators with different data types. Please see following table.

**Q: What combinations of operators and operands are valid? Which function does an operator have depending on its operands?**

Interpreter only recognizes the operations in the table below. An exception is thrown for incompatible data types.

| **Operator Type** | **Operator** | **Operand 1** | **Operand 2** | **Operation**            |
|-------------------|--------------|---------------|--------------|--------------------------|
| Unary             | -            | Number        |              | Negates the number       |
| Arithmetic        | +            | Number        | Number       | Addition                 |
| Arithmetic        | +            | String        | Any          | Concatenation **(\*)**   |
| Arithmetic        | -            | Number        | Number       | Subtraction              |
| Arithmetic        | *            | Number        | Number       | Multiplication           |
| Arithmetic        | /            | Number        | Number       | Division                 |
| Logical           | !            | Boolean       |             | Logical NOT              |
| Logical           | AND          | Boolean       | Boolean      | Logical AND              |
| Logical           | OR           | Boolean       | Boolean      | Logical OR               |
| Comparison        | ==           | Any           | Any          | Equality Check           |
| Comparison        | !=           | Any           | Any          | Inequality Check         |
| Comparison        | \>           | Number        | Number       | Greater than             |
| Comparison        | \>=          | Number        | Number       | Greater than or equal to |
| Comparison        | <            | Number        | Number       | Less than                |
| Comparison        | <=           | Number        | Number       | Less than or equal to    |

**(\*)** In String Concatenation, operands can be in any order. The important thing is that at least one of the operands is of type String.

**(\*\*)** Any: It can be a String, a Number or a Boolean.


**The reason for the decision:** To avoid operator overloading. Because the expressions like boolean + boolean are often confusing. The only exception ist '+' operator used for string concatenation.

**How to check:** Dynamically.

## 3- Redefinition of Variables
**Q: Can variables be defined more than once within the same scope?**

**A:** After its first declaration, a variable cannot be redeclared in the same scope and its inner scopes.

**The reason for the decision:** No important reason. Just a personal preference.

**How to check:** Dynamically. Before defining a new variable check if it has already been defined.


## 4- Shadowing and Scoping
**Q: Is the redefinition of variables in inner scopes allowed? Does this cause shadowing or overwriting of the outer variable?**

**A:** After its first declaration, a variable cannot be redeclared in the same scope and its inner scopes. A variable is valid in the scope in which it is declared and all its inner scopes. A variable that has not yet been declared cannot be used.

**The reason for the decision:** Redeclaration of variables and shadowing make it harder to keep track of the actual value of a variable when reading code.

**How to check:** Dynamically. Before defining a new variable check if it has already been defined.

## 5- Uninitialized Values
**Q: Can variables be used without being assigned an explicit value?**

**A:** Variables that are declared but not yet initialized have no default values. Therefore, before using a variable, it must be explicitly assigned a value. Otherwise, an exception is thrown.

**The reason for the decision:** Our language is a dynamically-typed language and there is no implicit conversion. We have only one keyword ('var') to variable declaration. Therefore, without assigning a value it is not possible to decide its data type. In this case, relying on default values could lead to errors.  

**How to check:** Assign 'null' to defined but not initialized variables while storing them (in Memory.java). Check the value of variable before using it. If it is still null, this means that, it has not yet initialized. 
