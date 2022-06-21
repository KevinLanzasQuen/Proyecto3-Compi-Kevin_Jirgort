/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciojflex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Andrey McCarty
 */
public class Mips {
    private StringBuffer sb=new StringBuffer();
    private GeneradorCodigoMips generador=new GeneradorCodigoMips();
    private boolean isMain=false;
    String nombre="codigoMips";
    
    public Mips(){
    
    }
    
    public void construirMips() throws FileNotFoundException, IOException{
       
        FileReader fr=null;
        File archivo=new File("src\\ejerciciojflex\\codTresDirecciones.txt");
        fr=new FileReader(archivo);
        
        BufferedReader br=new BufferedReader(fr);
        sb.append(".data\n"+".text\n"+".globl main\n");
        sb.append(generador.printF());
        recorrerTresDirecciones(br);
        
    }
    private void recorrerTresDirecciones(BufferedReader br) throws IOException {
        String linea;
        while((linea=br.readLine())!=null){
           
            if(isLineaVacia(linea)){
                  
                if(isInicioFunc(linea)){
                  
                   construirFuncion(br);
                   
                }
            
            }
        }
        
        sb.append(terminarMain());
       
        BufferedWriter bwr = null;
        bwr=new BufferedWriter(new FileWriter(new File("C:\\Users\\Andrey McCarty\\Desktop\\Nueva carpeta (2)\\Programa\\ProyectoCompiKevinLanzasJirgortMcCarty\\KevinLanzasQuenJirgortMcCartyProyectoCompi\\Proyecto#1Compi_Kevin_Jirgort\\src\\ "+nombre+".asm")));
        System.out.print(sb.toString());
        bwr.write(sb.toString());
        bwr.flush();
        bwr.close();
        
       
        //linea 77 y 78 ************************************************************************************************************************************
        
        
        
         //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //prepararScopeDeFuncion en el analizador
    private void construirFuncion(BufferedReader br) throws IOException{
        boolean notFin=true;
        String linea;
        
        sb.append(generador.getRa());
        
        while(notFin){
            linea=br.readLine();
            if(isLineaVacia(linea)){
               
                if(isFinalFunc(linea)){
                        notFin=false;
                
                }else{
                   
                    selectAccion(linea);
                    
                    
                
                }
            }else{
                System.out.println("esta vacio");
            }
        
        }
    }
    //analizar3D en el analizador
    private void selectAccion(String linea){
       
       if(isEtiq(linea)){
           
           sb.append(linea + "\n");
       }else if (call(linea)){
           hacerCall(linea);
       }else if(pop(linea)){
           hacerPop(linea);
           
       }else if(isPalabra(linea, "goto")){
           Goto(linea);
       }else if(isPalabra(linea, "if")){
           If(linea);
       }else if(isPalabra(linea, "param")){
           param(linea);
       }else if (isDec(linea)){
           hacerDec(linea);
       }else if(isArg(linea)){
           hacerArg(linea);
       }else if(instruccion(linea)){
          
           hacerInstruccion(linea);
       }else if(isPalabra(linea,"return")){
           hacerReturn(linea);
       }else if(etiqueta(linea)){
           hacerEtiqueta(linea);
       }else if(isPalabra(linea,"ifFalse")){
           hacerEstructura(linea);
       }else{
           //System evento no capturado
       }
   //falta agragarle los demas y crear las funciones isPalabra, isDec, isArg, param, iF, goto, hacerDec***********************************************************
        
    }
    
    
    private String terminarMain(){
        String fin="end:\n"+"li $v0,10\n"+"syscall";
        return fin;
        
    
    }
    private boolean isLineaVacia(String linea){
        
        if(linea==null ||linea.equals("")||linea.split("", 0)[0].equals("")){
            return false;
            
        }else{
                return true;
            
        }
    }
    
    private boolean isFinalFunc(String linea){
     String[] endFunc=linea.split(" ", 0);
     
     if(endFunc[0].equals("end")&& endFunc[1].equals("func")){
         
         return true;
     
     
     }else{
         return false;
     
     }
    
    
    }
    private boolean isInicioFunc(String linea){
        String[] inicioFunc=linea.split(" ", 0);
        
        if(inicioFunc[0].equals("func")&& inicioFunc[1].equals("begin")){
             
            sb.append(linea.split(" ")[2]+":"+"\n");
            if(inicioFunc[2].equals("main")) {
                
                isMain=true;
            }
            return true;
     
     
        }else{
            return false;
     
        }
    
    
    }
     public boolean isEtiq(String linea){
        String [] lista = linea.split(" ",0);
        if(lista.length == 1){
            return true;
        }else{
            return false;
        }
    }
    public boolean isPalabra(String linea, String etiqueta){
        String[] lista = linea.split(" ",0);
        if(lista[0].equals(etiqueta)){
            return true;
        }else{
            return false;
        } 
    }
    private void Goto(String linea){
        String[] lista = linea.split(" ",0);
        if(lista[1].charAt(lista[1].length()-1) == ':'){
            sb.append("j " + lista[1].substring(0,lista[1].length()-1)+"\n");
        }else{
            sb.append("j " + lista[1] + "\n");
        }
    }
    public void If(String linea){
        sb.append(generador.obtenerIf(linea));
    }
    
    private void param(String linea){
        sb.append(generador.obtenerParametro(linea));
    }
    
    private boolean isDec(String linea){
        String[] lista = linea.split(" ",0);
        if((lista.length == 3) && lista[2].substring(0,1).equals("t")){
            return true;
        }else{
            return false;
        }
    }
    
    private void hacerDec(String linea){
        sb.append(generador.obtenerDeclaracion(linea));
    }
    
    private boolean isArg(String linea){
        String[] lista = linea.split(" ",0);
        if(lista.length == 4){
            if(lista[2].equals("param")){
                return true;
            }
        }else{
            return false;
        }
        return false;
    }
    
    private void hacerArg(String linea){
        sb.append(generador.obtenerArgumento(linea));
    }
    
    private boolean call(String linea){
        String[] lista = linea.split(" ",0);
        if(lista.length>2){
            if(lista[2].equals("call")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    private void hacerCall(String linea){
        sb.append(generador.obtenerCall(linea));
    }
    
    private boolean pop(String linea){
        String[] lista = linea.split(" ",0);
        if(lista.length == 3 && Character.isLetter(lista[2].charAt(0))&& !lista[2].equals("true")&& !lista[2].equals("false")){
            if(lista[2].length()>1){
                if(Character.isLetter(lista[2].charAt(1))){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    public void hacerPop(String linea){
        sb.append(generador.obtenerPop(linea));
        
    }
    
    private boolean instruccion(String linea){
        String[] lista = linea.split(" ",0);
        if(lista[0].substring(0,1).equals("t")&&!pop(linea)&&!Character.isLetter(lista[0].charAt(1))){
            return true;
        }else{
            return false;
        }
    }
    
    public void hacerInstruccion(String linea){
        sb.append(generador.obtenerInstruccion(linea));
    }
    
    public void hacerReturn(String linea){
        if(isMain){
            sb.append("jal end\n");
            isMain = false;
        }
        else{
            sb.append(generador.obtenerRetorno(linea));
        }
    }
    
    public boolean etiqueta(String linea){
        String[] lista = linea.split(" ", 0);
        if(lista.length == 1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void hacerEtiqueta(String linea){
        sb.append(linea+"\n");
    }
    
     private void hacerEstructura(String linea){
        sb.append(generador.obtenerEstructura(linea));
    }
}
