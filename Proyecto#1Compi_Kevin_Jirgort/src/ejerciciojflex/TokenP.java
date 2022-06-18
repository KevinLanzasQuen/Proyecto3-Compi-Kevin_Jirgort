package ejerciciojflex;


   public class TokenP{
        String tokent;
        String lexemal;
        String arbola;

    public TokenP(String lexema, String token,String arbol) {
        this.lexemal=lexema;
        this.tokent=token; 
        this.arbola=arbol;
    }
    public String getToken(){
        return this.tokent;
    }
        
    public String toString(){
        return "Lexema: "+ this.lexemal + "  Token: " +"["+ this.tokent+"]" + " Tabla de simbolos: "+ this.arbola ;
    }
   }


			

    

