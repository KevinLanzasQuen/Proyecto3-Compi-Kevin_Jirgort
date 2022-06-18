/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciojflex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Andrey McCarty
 */
public class GeneradorTresDirecciones {
    public static final int MAS=1;
    public static final int MENOS=2;
    public static final int POR=3;
    public static final int ASIG=4;
    public static final int DIVISION=5;
    public static final int GOTO=6;
    public static final int LABEL=7;
    public static int contTemp=0;
    public static int contEtiqueta=0;
    
    
    public static final int PRINT=8;
    public static final int  IFEQUAL=9;
    public static final int IFMENOR=10;
    public static final int IFMENORIGUAL=11;
    public static final int IFMAYOR=12;
    public static final int IFMAYORIGUAL=13;
    public static final int READ=14;
    public static final int FUNCION=15;
    public static final int PARAMETRO=16;
    public static final int CIERREFUNCION=17;
    
    public static void generador(int operacion,String argumento1,String argumento2, String resultado) throws IOException{
        //File archivoN=new File("C:/Users/Andrey McCarty/Desktop/Nueva carpeta (2)/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/codTresDirecciones.txt");
        File archivoN=new File("C:/Users/kevin/OneDrive/Desktop/PP2_KevinLanzas_JirgortMcarty/Programa/ProyectoCompiKevinLanzasJirgortMcCarty/KevinLanzasQuenJirgortMcCartyProyectoCompi/Proyecto#1Compi_Kevin_Jirgort/src/ejerciciojflex/codTresDirecciones.txt");
        FileWriter escribir=new FileWriter(archivoN,true);
        
        switch(operacion){
            case MAS:
                escribir.write(resultado+ " = "+argumento1+" + "+argumento2+ "#");
                //System.out.println(" "+resultado+ " = "+argumento1+" + "+argumento2+ "#");
                break;
            case MENOS:
                 escribir.write(resultado+ " = "+argumento1+" - "+argumento2+ "#");
                 //System.out.println(" "+resultado+ " = "+argumento1+" - "+argumento2+ "#");
                 break;
            case POR:
                escribir.write(resultado+ " = "+argumento1+" * "+argumento2+ "#");
                //System.out.println(" "+resultado+ " = "+argumento1+" * "+argumento2+ "#");
                break;
             case DIVISION:
                escribir.write(resultado+ " = "+argumento1+" / "+argumento2+ "#");
                //System.out.println(" "+resultado+ " = "+argumento1+" / "+argumento2+ "#");
                break;
                
             case ASIG:
                escribir.write(resultado+ " = "+argumento1+"#");
                //System.out.println(" "+resultado+ " = "+argumento1+"#");
                break;
            case GOTO:
                escribir.write("goto "+ resultado+"#");
                //System.out.println("goto "+ resultado+"#");
                break;
            case IFEQUAL:
                escribir.write(" if("+argumento1+" == "+argumento2+") goto "+resultado+ "#");
                //System.out.println(" if("+argumento1+" == "+argumento2+") goto "+resultado+ "#");
                break;
            case IFMENOR:
                escribir.write(" if("+argumento1+" < "+argumento2+") goto "+resultado+ "#");
                //System.out.println(" if("+argumento1+" < "+argumento2+") goto "+resultado+ "#");
                break;
            case IFMENORIGUAL:
                escribir.write(" if("+argumento1+" <= "+argumento2+") goto "+resultado+ "#");
                //System.out.println(" if("+argumento1+" <= "+argumento2+") goto "+resultado+ "#");
                break;   
            case IFMAYOR:
                escribir.write(" if("+argumento1+" > "+argumento2+") goto "+resultado+ "#");
               //System.out.println(" if("+argumento1+" > "+argumento2+") goto "+resultado+ "#");
                break;
             case IFMAYORIGUAL:
                escribir.write(" if("+argumento1+" >= "+argumento2+") goto "+resultado+ "#");
                //System.out.println(" if("+argumento1+" >= "+argumento2+") goto "+resultado+ "#");
                break;
            case LABEL:
                escribir.write(resultado+":");
                //System.out.println(resultado+":");
                break;
            case PRINT:
                escribir.write(" print "+resultado+"#");
                //System.out.println("print "+ resultado+"#");
                break;
             case READ:
                escribir.write(" read "+resultado+"#");
                //System.out.println("print "+ resultado+"#");
                break;
             case FUNCION:
                 escribir.write("funcion_begin_" + argumento1);
                 break;
             case PARAMETRO:
                 escribir.write("Param " + argumento1 );
                 break;
             case CIERREFUNCION:
                 escribir.write("funcion_" + argumento1 + "_end" );
                 break;
            
        }
        escribir.write("\r\n");
        escribir.close();
    
    }
    public static String nuevaTemp(){
        return "t"+contTemp++;
    }
    public static String nuevaEtiqueta(){
        return "L"+contEtiqueta++;
    }
}
