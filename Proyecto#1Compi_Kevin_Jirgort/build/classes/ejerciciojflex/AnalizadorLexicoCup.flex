package ejerciciojflex;
import java_cup.runtime.Symbol;
%%
%class AnalizadorLexicoCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char

boolean= ("true" | "false")+
tipo = "double"
Asig="="
letra=[a-zA-Z] 
nulo="null"
digR=[1-9]
Dig=[0-9]
L=[a-zA-Z_]+
D=[0-9]+

comillaA='
comillaC='
comillas="\""
String= ({comillas}({letra}|{Dig}|{simb}|{operadores}|{ExprLogicas}|{comparadores}|{Separadores}|{ExprFinal}) ({letra} | {Dig}| {simb}|{operadores}|{ExprLogicas}|{comparadores}|{Separadores}|{ExprFinal}|{Espacio} )*{comillas}) 

operadores="*"|"+"|"-"|"/"
Separadores=")"|"("|"}"|"{"|"["|"]"|","|":"
simb = "?" |"$"|"~"|"@"|";"|":"|"%"|"$"|"%"
Espacio = " "|"	"

id= [a-zA-Z][a-zA-Z0-9]*

ExprLogicas="|"|"&"|"!"
comparadores= ">"|"<"|"=="|">="|"<="|"!="
ExprFinal="#"
punto="."
flotante=(({Dig} {Dig}*)) {punto}{Dig}+
Entero= ({digR} {Dig}*) | 0
Comentario = "//" ({id}{Espacio})*(\r|\n|\r\n)?
ComentarioMultilinea="/*" [^*] ~"*/" 
char= {comillaA} ({letra}|{simb}|"!") {comillaC}
ParentesisAb="("
ParentesisCe=")"    
Coma=","            



Parametros= ({id}|{Entero}|{char}|{flotante}|{String}|{boolean}) ((coma({id}|{Entero}|{char}|{flotante}|{String}|{boolean}))*)?
SaltoDeLinea = \n|\r|\r\n

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%
{Espacio} {
 /*Ignore*/
}


{ComentarioMultilinea} {
 /*Ignore*/
}

{tipo} {return new Symbol(sym.Tipo, yychar, yyline, yytext());}

("int") {return new Symbol(sym.Int, yychar, yyline, yytext());}

("float") {return new Symbol(sym.Float, yychar, yyline, yytext());}

("bool") {return new Symbol(sym.Bool, yychar, yyline, yytext());}

("char") {return new Symbol(sym.CharType, yychar, yyline, yytext());}

("string") {return new Symbol(sym.String, yychar, yyline, yytext());}

("if") {return new Symbol(sym.If, yychar, yyline, yytext());}

("else") {return new Symbol(sym.Else, yychar, yyline, yytext());}

("read") {return new Symbol(sym.Read, yychar, yyline, yytext());}

("while") {return new Symbol(sym.While, yychar, yyline, yytext());}

("for") {return new Symbol(sym.For, yychar, yyline, yytext());}

("switch") {return new Symbol(sym.Switch, yychar, yyline, yytext());}

("return") {return new Symbol(sym.Return, yychar, yyline, yytext());}

("break") {return new Symbol(sym.Break, yychar, yyline, yytext());}

("case") {return new Symbol(sym.Case, yychar, yyline, yytext());}

("default") {return new Symbol(sym.Default, yychar, yyline, yytext());}

("print") {return new Symbol(sym.Print, yychar, yyline, yytext());}

("*") {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}

("+") {return new Symbol(sym.Suma, yychar, yyline, yytext());}

("-") {return new Symbol(sym.Resta, yychar, yyline, yytext());}

("/") {return new Symbol(sym.Division, yychar, yyline, yytext());}

(")") {return new Symbol(sym.ParentesisC, yychar, yyline, yytext());}

("(") {return new Symbol(sym.ParentesisA, yychar, yyline, yytext());}

("}") {return new Symbol(sym.LlaveC, yychar, yyline, yytext());}

("{") {return new Symbol(sym.LlaveA, yychar, yyline, yytext());}

("[") {return new Symbol(sym.CorcheteA, yychar, yyline, yytext());}

("]") {return new Symbol(sym.CorcheteC, yychar, yyline, yytext());}

(",") {return new Symbol(sym.Coma, yychar, yyline, yytext());}

(":") {return new Symbol(sym.DosPuntos, yychar, yyline, yytext());}

("==") {return new Symbol(sym.IgualIgual, yychar, yyline, yytext());}

("!=") {return new Symbol(sym.Diferente, yychar, yyline, yytext());}

("<") {return new Symbol(sym.Menor, yychar, yyline, yytext());}

("<=") {return new Symbol(sym.MenorIgual, yychar, yyline, yytext());}

(">") {return new Symbol(sym.Mayor, yychar, yyline, yytext());}

(">=") {return new Symbol(sym.Mayor, yychar, yyline, yytext());}

("!") {return new Symbol(sym.Negacion, yychar, yyline, yytext());}

("||") {return new Symbol(sym.Or, yychar, yyline, yytext());}

("&&") {return new Symbol(sym.And, yychar, yyline, yytext());}

("++"|"--") {return new Symbol(sym.DecInc, yychar, yyline, yytext());}

("main") {return new Symbol(sym.main, yychar, yyline, yytext());}
{Asig} {
 return new Symbol(sym.Asig, yychar, yyline, yytext());
}
{nulo} {
 return new Symbol(sym.Nulo, yychar, yyline, yytext());
}

{ExprFinal} {
 return new Symbol(sym.ExprFinal, yychar, yyline, yytext());
}



{char}* {
    return new Symbol(sym.Char, yychar, yyline, yytext());
}

{ExprLogicas} {
 return new Symbol(sym.ExprLogica, yychar, yyline, yytext());
}



{boolean}* {
 return new Symbol(sym.Boolean, yychar, yyline, yytext());

}

{flotante}* {
 return new Symbol(sym.Flotante, yychar, yyline, yytext());
}
{Entero}* {
 return new Symbol(sym.Entero, yychar, yyline, yytext());
}

{String}* {
 return new Symbol(sym.Cadena, yychar, yyline, yytext());
}

{comillas} {
 return new Symbol(sym.Comillas, yychar, yyline, yytext());
}
{Espacio} {
 /*Ignore*/
}

 {id} {
    return new Symbol(sym.Id, yychar, yyline, yytext());
}
{Parametros} {
 return new Symbol(sym.Parametros, yychar, yyline, yytext());
}
{Comentario} {
 /*Ignore*/
}
{SaltoDeLinea} {
 /*Ignore*/
}

/*. {return new Symbol(sym.ERROR, yychar, yyline, yytext());}*/
<YYINITIAL> . {
String errLex = "Error , caracter irreconocible: '"+yytext()+"' en la linea: "+(yyline+1)+" y columna: "+(yycolumn+1) ;
System.err.println(errLex);
}
