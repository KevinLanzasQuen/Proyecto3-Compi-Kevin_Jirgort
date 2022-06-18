package ejerciciojflex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java_cup.runtime.Symbol;

/**
 *
 * @author Andrey McCarty
 */
public class PruebaJflex {
 
  public static void main(String[] args) throws Exception {
      
      //String ruta="C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/AnalizadorLexico.flex";
      String ruta="C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/AnalizadorLexico.flex";
      //String ruta1="C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/AnalizadorLexicoCup.flex";
      String ruta1="C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/AnalizadorLexicoCup.flex";
      //String[] ruta2={"-parser","Sintax","C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/Sintax.cup"};
      String[] ruta2={"-parser","Sintax","C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/Sintax.cup"};
      generarLexer(ruta,ruta1, ruta2);
      
      
  try{
      
  //String archivo = "C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/codigo.txt";
  String archivo = "C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/codigo.txt";
  BufferedReader buffer = new BufferedReader(new FileReader(archivo));
  AnalizadorLexico analizadorJFlex = new AnalizadorLexico(buffer);
  
  int identificadorChar = 1;
  int identificadorString = 1;
  int identificadorEntero = 1;
  int identificadorOperador = 1;
  int identificadorFlotante = 1;
  int identificadorId = 1;
  int identificadorBool = 1;
  int identificadorComp = 1;
  int identificadorExpL = 1;
  int identificadorExpF = 1;
  int identificadorSeparador = 1;
  int identificadorNulo= 1;
  int identificadorAsig= 1;
  int identificadorTipo= 1;
  int identificadorEstC= 1;
  while(true){
  TokenP token = analizadorJFlex.yylex();
  if (!analizadorJFlex.existenTokens())
   break;
  
  //File archivoN=new File("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/tokens.txt");
  File archivoN=new File("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/tokens.txt");
  FileWriter escribir=new FileWriter(archivoN,true);
  
  if(token.arbola =="ENTERO"){
      escribir.write("["+token.arbola +", "+ String.valueOf(identificadorEntero)+"], "+token.lexemal);
      identificadorEntero=identificadorEntero+1;
  }else{
      if(token.arbola=="CHAR"){
           escribir.write("["+token.arbola +", "+ String.valueOf(identificadorChar)+"], "+token.lexemal);
           identificadorChar=identificadorChar+1;
          
      
      }else{
          if(token.arbola=="STRING"){
              escribir.write("["+token.arbola +", "+ String.valueOf(identificadorString)+"], "+token.lexemal);
              identificadorString=identificadorString+1;
          
          }else{
              if(token.arbola=="OPERADOR"){
                  escribir.write("["+token.arbola +", "+ String.valueOf(identificadorOperador)+"], "+token.lexemal);
                  identificadorOperador=identificadorOperador+1;
              
              }else{
                  if(token.arbola=="FLOTANTE"){
                    escribir.write("["+token.arbola +", "+String.valueOf(identificadorFlotante)+"], "+token.lexemal);
                    identificadorFlotante=identificadorFlotante+1;  
                  }else{
                      if(token.arbola=="IDENTIFICADOR"){
                          escribir.write("["+token.arbola +","+ String.valueOf(identificadorId)+"], "+token.lexemal);
                          identificadorId=identificadorId+1;
                      }else{
                          if(token.arbola=="BOOLEAN"){
                              escribir.write("["+token.arbola +","+ String.valueOf(identificadorBool)+"], "+token.lexemal);
                              identificadorBool=identificadorBool+1;
                            }else{
                              if(token.arbola=="COMPARADOR"){
                                  escribir.write("["+token.arbola +","+ String.valueOf(identificadorComp)+"], "+token.lexemal);
                                  identificadorComp=identificadorComp+1;
                              
                              }else{
                                  if(token.arbola=="EXPRESION_LOGICA"){
                                      escribir.write("["+token.arbola +","+ String.valueOf(identificadorExpL)+"], "+token.lexemal);
                                      identificadorExpL=identificadorExpL+1;
                                  }else{
                                      if(token.arbola=="FINAL_EXPRESION"){
                                          escribir.write("["+token.arbola +","+ String.valueOf(identificadorExpF)+"], "+token.lexemal);
                                          identificadorExpF=identificadorExpF+1;
                                          
                                      }else{
                                          if(token.arbola=="SEPARADOR"){
                                              escribir.write("["+token.arbola +","+ String.valueOf(identificadorSeparador)+"], "+token.lexemal);
                                              identificadorSeparador=identificadorSeparador+1;
                                          }else{
                                              if(token.arbola=="NULO"){
                                                  escribir.write("["+token.arbola +","+ String.valueOf(identificadorNulo)+"], "+token.lexemal);
                                                  identificadorNulo=identificadorNulo+1;
                                              }else{
                                                  if(token.arbola=="ASIGNACION"){
                                                      escribir.write("["+token.arbola +","+ String.valueOf(identificadorAsig)+"], "+token.lexemal);
                                                      identificadorAsig=identificadorAsig+1;
                                                  
                                                  }else{
                                                      if(token.arbola=="TIPO_DATO"){
                                                          escribir.write("["+token.arbola +","+ String.valueOf(identificadorTipo)+"], "+token.lexemal);
                                                          identificadorTipo=identificadorTipo+1;
                                                      }else{
                                                          if(token.arbola=="ESTRUCTURA_CONTROL"){
                                                              escribir.write("["+token.arbola +","+ String.valueOf(identificadorEstC)+"], "+token.lexemal);
                                                              identificadorEstC=identificadorEstC+1;
                                                          }
                                                      }
                                                  }
                                              }
                                          }
                                      }
                                  }
                              }
                          }
                      }
                  }
              }
          }
      }
  }
      
 
  escribir.write("\r\n");
  escribir.close();
 
  
 }
 }
 catch (Exception e){
   System.out.println(e.toString());
 }
 }
  public static void generarLexer(String ruta,String ruta1, String[] ruta2) throws IOException, Exception{
     
     
     JFlex.Main.generate(new File(ruta));
    
     JFlex.Main.generate(new File(ruta1));
    
     java_cup.Main.main(ruta2);
    //Path rutaSym = Paths.get("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/sym.java");
    Path rutaSym = Paths.get("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/sym.java");

        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
             
        }
        Files.move(
                //Paths.get("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/sym.java"), 
                //Paths.get("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/sym.java")
                Paths.get("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/sym.java"), 
                Paths.get("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/sym.java")
        );
        //Path rutaSin = Paths.get("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/Sintax.java");
        Path rutaSin = Paths.get("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        
        Files.move(
                //Paths.get("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/Sintax.java"), 
                //Paths.get("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/Sintax.java")
                Paths.get("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/Sintax.java"), 
                Paths.get("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/Sintax.java")
        
        );
        //String archivo = "C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/codigo.txt";
        String archivo = "C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/codigo.txt";
        FileReader fl=new FileReader(archivo);
        BufferedReader buffer = new BufferedReader(new FileReader(archivo));
        Sintax s = new Sintax(new ejerciciojflex.AnalizadorLexicoCup(buffer));

        try {
           
            s.parse();
            //ArrayList<Object> listaIdentificadores2 = s.listaIdentificadores;
            if(s.contErr>0){
                
                 //File archivoN=new File("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/codTresDirecciones.txt");
                 File archivoN=new File("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/codTresDirecciones.txt");
                 archivoN.delete();
                 archivoN.createNewFile();
            }
            //System.out.println(Integer.valueOf(listaIdentificadores2.get(8).toString()).getClass().getSimpleName() + listaIdentificadores2.size());
            //System.out.println(Integer.valueOf(listaIdentificadores2.get(9).toString()).getClass().getSimpleName());
            
                System.out.println("Analisis sintactico realizado correctamente");
        } catch (Exception ex) {
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
         
        }
        
  }
  
}
