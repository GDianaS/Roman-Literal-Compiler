# dl-short-4
Este projeto é uma versão resumida da linguagem DL.  
Esta versao faz as análises léxica, sintática e semântica e a geração de código intermediário.  
Ela está de acordo com a gramática abaixo.  

GITHUB: https://github.com/GDianaS/Roman-Literal-Compiler
Acadêmica: Gabriela Diana Sena de Sousa
Disciplina: Compiladores

## Adicional
Este projeto conta com o recurso de algoritmos romanos.

## Gramática
```
PROGRAM				::= programa ID BLOCK  
BLOCK				::= inicio STMTS fim  
STMTS				::= STMT; STMTS | ε  
STMT				::= BLOCK | DECL | ASSIGN | WRITE | IF  
DECL     			::= TYPE ID  
ASSIGN   			::= ID = EXPR  
WRITE				::= escreva(ID)  
IF					::= se (EXPR) STMT  
EXPR				::= EXPR "|" REL | REL | ROM
REL					::= REL < ARITH | REL <= ARITH | REL > ARITH | ARITH  
ARITH  				::= ARITH + TERM | ARITH - TERM | TERM  
TERM				::= TERM * FACTOR | FACTOR  
FACTOR				::= (EXPR) | ID | LIT_INT | LIT_REAL | LIT_BOOL
```
## Definições Regulares
```
LETTER		::= a | b | ... | z | A | B | ... Z |
DIGIT		::= 0 | 1 | ... | 9  
ID			::= LETTER (LETTER | DIGIT)*  
LIT_INT		::= DIGIT+  
LIT_REAL	::= DIGIT+ . DIGIT+   
LIT_BOOL	::= verdadeiro | falso  
TYPE     	::= inteiro | real | booleano  
```
## Algoritmos Romanos
```
ROM           ::= 0r(LIT_ROM)+   
LIT_ROM       ::= i | v | x | l | c | d | m   
```

## Exemplos de Entradas

Para rodar o projeto, deve-se executar o arquivo DL.java, o qual irá relizar a análise do pesudocódigo do arquivo prog.dl;

### Exemplo 1

O pseudocódigo abaixo é um exemplo no qual os literais romanos são todos aceitos pelo compilador.
```
programa teste inicio
  inteiro a;
  inteiro b;
  a = 0rii;
  b = 0rxxx;
fim.
```

### Exemplo 2

O projeto aceita até o número 4999, assim, para a atribuição de um valor maior do que 3999 faz-se o uso de letras maiúsculas para a definição de qual a quantidade de milhares. No caso abaixo, o algarismo romano IVxx, tem-se que a quantidade de milhares é de IV, ou seja de 4000.
```
programa teste inicio
  inteiro a;
  a = 0rIVxx;
fim.
```
### Exemplo 3

Esse terceiro exemplo apresenta um algoritmo romano inválido pelo compilador devido a um número inadequado de algarismos (i).
```
programa teste inicio
  inteiro a;
  a = 0riiii;
fim.
```