package ejerciciojflex;
import ejerciciojflex.TokenP;
%%
%public
%class AnalizadorLexico
%type TokenP
%line
%char
%column
%{
 
 

 private boolean _existenTokens = false;
 
 public boolean existenTokens(){
 return this._existenTokens;
 }
 
%}
 

%type TokenP
 
%init{
%init}
 
%eof{
 
 this._existenTokens = false;
 
%eof}
 
/* Inicio de Expresiones regulares */

boolean= "true"| "false"
tipo = "int"| "string" | "boolean" | "float"|"char"|"double"
estruControl="if"|"else"|"switch"|"return"|"break"|"case"|"while"|"default"|"print"|"for"
Asig="="
letra=[A-Za-z]
nulo="null"
digR=[1-9]
dig=[0-9]
comillaA='
comillaC='
string= {comillaA}({letra}|{dig}|{simb}|{operadores}|{ExprLogicas}|{comparadores}|{Separadores}|{ExprFinal}) ({letra} | {dig}| {simb}|{operadores}|{ExprLogicas}|{comparadores}|{Separadores}|{ExprFinal}|{Espacio} )*{comillaC} 
simb = "?" |"$"|"~"|"@"|";"|":"|"%"|"$"|"%"

Espacio = " "|"	"
id= {letra}({letra}|{digR})*
operadores="*"|"+"|"-"|"/"
ExprLogicas="|"|"&"|"!"
comparadores=">"|"<"|"=="|">="|"<="|"!="
ExprFinal="#"
Separadores=")"|"("|"}"|"{"|"["|"]"|","|":"
sigN="-"

punto="."
flotante=({sigN} {dig}*|({dig} {dig}*)) {punto}{dig}+
entero= {sigN} {dig}* |({digR} {dig}*)

Comentario = "//" ({id}{Espacio})*(\r|\n|\r\n)?

ComentarioMultilinea="/*" [^*] ~"*/" 



char= {comillaA} {letra} {comillaC}



 


 SaltoDeLinea = \n|\r|\r\n
 
/* Fin de expresiones regulares */
 
%%
 
/* Reglas */
{Comentario} {
 
}
{ComentarioMultilinea} {
 
}
{tipo} {
 TokenP t = new TokenP(yytext(),yytext(), "TIPO_DATO");
 this._existenTokens = true;
 return t;
}
{estruControl} {
 TokenP t = new TokenP(yytext(),yytext(), "ESTRUCTURA_CONTROL");
 this._existenTokens = true;
 return t;
}
{Asig} {
 TokenP t = new TokenP(yytext(),yytext(), "ASIGNACION");
 this._existenTokens = true;
 return t;
}
{nulo} {
 TokenP t = new TokenP(yytext(),yytext(), "NULO");
 this._existenTokens = true;
 return t;
}
{ExprFinal} {
 TokenP t = new TokenP(yytext(),yytext(), "FINAL_EXPRESION");
 this._existenTokens = true;
 return t;
}

{Separadores} {
 TokenP t = new TokenP(yytext(),yytext(), "SEPARADOR");
 this._existenTokens = true;
 return t;
}

{ExprLogicas} {
 TokenP t = new TokenP(yytext(),yytext(), "EXPRESION_LOGICA");
 this._existenTokens = true;
 return t;
}

{comparadores} {
 TokenP t = new TokenP(yytext(),yytext(), "COMPARADOR");
 this._existenTokens = true;
 return t;
}
{boolean}* {
 TokenP t = new TokenP(yytext(),yytext(), "BOOLEAN");
 this._existenTokens = true;
 return t;
}

{flotante} {
 TokenP t = new TokenP(yytext(),yytext(), "FLOTANTE");
 this._existenTokens = true;
 return t;
}
{entero} {
 TokenP t = new TokenP(yytext(),yytext(), "ENTERO");
 this._existenTokens = true;
 return t;
}

{string} {
 TokenP t = new TokenP(yytext(),yytext(), "STRING");
 this._existenTokens = true;
 return t;
}
{Espacio} {
 
}
{id} {
 TokenP t = new TokenP(yytext(),yytext(), "IDENTIFICADOR");
 this._existenTokens = true;
 return t;
}
{char}* {
 TokenP t = new TokenP(yytext(),yytext(), "CHAR");
 this._existenTokens = true;
 return t;
}


{operadores} {
 TokenP t = new TokenP(yytext(),yytext(), "OPERADOR");
 this._existenTokens = true;
 return t;
}


 
{SaltoDeLinea} {
 TokenP t = new TokenP("Enter","Enter", "NUEVA_LINEA");
 this._existenTokens = true;
 return t;
}
<YYINITIAL> . {
/*String errLex = "Error lexico, caracter irreconocible: '"+yytext()+"' en la linea: "+(yyline+1)+" y columna: "+(yycolumn+1) ;
System.err.println(errLex);*/
}