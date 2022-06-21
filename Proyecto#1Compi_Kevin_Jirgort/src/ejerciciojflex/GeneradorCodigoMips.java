/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciojflex;

import java.util.ArrayList;

/**
 *
 * @author Andrey McCarty
 */
public class GeneradorCodigoMips {
    public ArrayList<Cuadruplo>registros=new ArrayList<Cuadruplo>();
    public ArrayList<Cuadruplo>registrosGuardados=new ArrayList<Cuadruplo>();
    public ArrayList<Cuadruplo>arg=new ArrayList<Cuadruplo>();
    public ArrayList<Cuadruplo>pila=new ArrayList<Cuadruplo>();
    public int ultimoRa=0;
    private boolean mul = false;
    private boolean div = false;
    private int rc = 0;
    public GeneradorCodigoMips(){
        cargarRegistrosT();
        cargarRegistrosA();
        cargarRegistrosS();
    
    }
    public String printF(){
        String print="print:\n"+registroRetornoP()+"li $v0,1\n"+"syscall\n"+"addi $sp, $sp, "+String.valueOf((pila.size()-1)*4-ultimoRa*4)+"\n"+"lw $t0, 0($sp)\n"+"move $ra, $t0\n"+"jr $ra\n\n";
        eliminarElemento();
        return print;
        
    }
    private void eliminarElemento(){
        int i=0;
        pila.subList(ultimoRa, pila.size()).clear();
        if(pila.size()!=0){
            for(i=pila.size()-1;pila.get(i).id.equals("ra");i--){
                    ultimoRa=pila.get(i).posicion;
            }
        
        }
    }
    private String registroRetornoP(){
        String regR="sub $sp, $sp, 4\n"+"sw $ra, 0($sp)\n";
        Cuadruplo elementoRa=new Cuadruplo();
        elementoRa.id="ra";
        elementoRa.posicion=pila.size();
        pila.add(elementoRa);
        ultimoRa=elementoRa.posicion;
        return regR;
        
        
    
    
    }
    public String getRa(){
        String cod="";
        cod=registroRetornoP();
        return cod;
        
    }
    public String obtenerIf(String linea){
        String cod ="";
        cod = construirIf(linea);
        return cod;
    }
    
    private String construirIf(String linea){
        String cod="";
        String [] lista = linea.split(" ",0);
        cod += "beq " + obtenerRegistro(lista[1]+",1"+", "+ lista[3]+"\n");
        liberarRegistro(lista[1]);
        return cod;
    } 
    
    private String obtenerRegistro(String id){
        String registro = "";
         
        for(Cuadruplo cuadru: registros){
            
            if(cuadru.id.equals(id)){
                //System.out.println("este es el id: "+ cuadru.id+" este es el idparam:"+ id+" este es el argumento: "+ cuadru.argumento1);
                registro = cuadru.argumento1;
            }
        }
        return registro;
    }
    
    private int liberarRegistro(String id){
       
        for(int i = 0; i<8;i++){
           
            
            if(registros.get(i).id.equals(id)){
                 
                registros.get(i).id = "";
                return 0;
            }
        }
        return 0;
    }
    
    public String obtenerParametro(String linea){
        String cod = "";
        cod = construccionParametro(linea);
        return cod;
    }
    
    private String construccionParametro(String linea){
        String cod = "";
        String[] lista = linea.split(" ",0);
        cod += "move "+ guardarArgumento("param")+ ", "+obtenerRegistro(lista[1])+"\n";
        liberarRegistrosParametros(lista[1]);
        return cod;
    }
    
    private String guardarArgumento(String id){
        String argumento ="";
        for(Cuadruplo cuadru : arg){
            if(cuadru.id.equals("")){
                cuadru.id = id;
                argumento = cuadru.argumento1;
                return argumento;
            }
        }
        return argumento;
    }
    
    void liberarRegistrosParametros(String parametro){
        String parametroLib = String.valueOf(Integer.parseInt(parametro.substring(1))-1);
        int contador = Integer.parseInt(parametro.substring(1));
        while(contador>=0){
            liberarRegistro("t"+String.valueOf(contador));
            contador--;
        }
    }
    
    public String obtenerDeclaracion(String linea){
        String cod="";
        cod = construccionDec(linea);
        return cod;
    }
    
    public String construccionDec(String linea){
        String cod ="";
        String[] lista = linea.split(" ",0);
        int var = posicionEnPila(lista[0]);
        if(var == -1){
            Cuadruplo cuadru = new Cuadruplo();
            cuadru.id = lista[0];
            cuadru.argumento1 = lista[2];
            cuadru.posicion = pila.size();
            pila.add(cuadru);
            cod +="sub $sp, $sp, 4 \n";
            
            String temp = obtenerRegistro(lista[2]);
            if(temp.equals("")){
                cod+= "sw $v0, 0($sp) \n\n";
            }else{
                cod += "sw " + obtenerRegistro(lista[2])+ ", 0($sp) \n\n";
            }
            liberarRegistro(lista[2]);
            return cod;
        }else{
            cod += "sw "+obtenerRegistro(lista[2])+", "+String.valueOf((pila.size()-1)*4- var*4)+"($sp) \n";
            liberarRegistro(lista[2]);
            return cod;
        }
    }
    
    private int posicionEnPila(String id){
        int pos = -1;
        for(Cuadruplo cuadru:pila){
            if(cuadru.id.equals(id)){
                pos = cuadru.posicion;
                return pos;
            }
        }
        return pos;
    }
    
    public String obtenerArgumento(String linea){
        String cod = "";
        String[] lista = linea.split(" ",0);
        cod += "sub $sp, $sp, 4\n";
        cod += "sw $a" +lista[3]+", 0($sp) \n";
        Cuadruplo cuadru = new Cuadruplo();
        cuadru.id = lista[0];
        cuadru.argumento1 = lista[2];
        cuadru.posicion = pila.size();
        pila.add(cuadru);
        return cod;
    }
    
    public String obtenerCall(String linea){
        String cod = "";
        String[] lista = linea.split(" ",0);
        cod += obtenerRegistrosTemp();
        cod += "jal "+lista[3] + "\n";
        cod += obtenerCargarRegTemp();
        
        cod += "move "+ guardarRegistros(lista[0]+", $v0 \n");
        arg = new ArrayList<Cuadruplo>();
        cargarRegistros();
        return cod;
    }
    
    public String obtenerRegistrosTemp(){
        String cod = "";
        int i = 0;
        for(Cuadruplo cuadru: registros){
            if(!cuadru.id.equals("")){
              registrosGuardados.get(i).id = cuadru.id;
              cuadru.id = "";
              cod += "move "+registrosGuardados.get(i).argumento1+", "+cuadru.argumento1+"\n";
              i++;
            }
        }
        return cod;
    }
    
    public String obtenerCargarRegTemp(){
        String cod = "";
        int i = 0;
        for(Cuadruplo cuadru: registrosGuardados){
            if(!cuadru.id.equals("")){
                registros.get(i).id = cuadru.id;
                cuadru.id = "";
                cod += "move "+registros.get(i).argumento1+", "+cuadru.argumento1+"\n";
                i++;
            }
        }
        return cod;
    }
    private String guardarRegistros(String id){
        Cuadruplo cuadru = new Cuadruplo();
        for(Cuadruplo cuadru2 : registros){
           
            if(cuadru2.id.equals("")){
                cuadru2.id = id;
                cuadru = cuadru2;
               
                return cuadru.argumento1;
            }
        }
        
        return cuadru.argumento1;
    }
    
    private void cargarRegistros(){
        Cuadruplo cuadru = new Cuadruplo();
        cuadru.argumento1 = "$a0";
        arg.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$a1";
        arg.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$a2";
        arg.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$a3";
        arg.add(cuadru);
    }
    
    public String obtenerPop(String linea){
        String cod = "";
        cod = construccionPop(linea);
       
        return cod;
    }
    
    private String construccionPop(String linea){
        String cod = "";
        String[] lista = linea.split(" ",0);
        
        cod += "lw " + guardarRegistros(lista[0])+ ", " + String.valueOf((pila.size()-1)*4 - obtenerVariable(lista[2]).posicion*4)+"($sp)\n";
       
        return cod;
    }
    
    private Cuadruplo obtenerVariable(String id){
        Cuadruplo cuadru = new Cuadruplo();
        for(Cuadruplo cuadru2 : pila){
            if(cuadru2.id.equals(id)){
                return cuadru2;
            }
        }
        return cuadru;
    }
    
    public String obtenerInstruccion(String linea){
        String cod = "";
        cod = construccionInstruccion(linea);
       
        return cod;
    }
    
    private String construccionInstruccion(String linea){
        String tipo = "";
        String[] lista = linea.split(" ", 0);
         
        if(lista.length > 3){
            tipo = operaciones(linea);
             
        }
        else{
            tipo = OperacionesConV(linea);
        }
        return tipo;
    }
    private String OperacionesConV(String line){
      
        String mips = "";
        String[] arr = line.split(" ", 0);
        String reg = ObtenerRegi(arr[2]);
        if(reg.equals("")){ //Si el registro no existe se agregar uno nuevo en uso
          
            if(arr[2].equals("true")){
                
                mips = "li "+ guardarRegistros(arr[0]) + ", 1 #Booleano true\n";
            }
            else if(arr[2].equals("false")){
                mips = "li "+ guardarRegistros(arr[0]) + ", 0 #Booleano false\n";
            }
            else{
                mips = "li "+ guardarRegistros(arr[0]) + ", " + arr[2] +  "\n";                
            }
        }
        else{
            mips = "move "+ guardarRegistros(arr[0]) + ", " + reg +  "\n";
            liberarRegistro(arr[2]);
        }        
        return mips;
    }
    private String ObtenerRegi(String id){
        String registro = "";
        for (Cuadruplo e : registros){
            if(e.id.equals(id)){
                registro = e.argumento1;
            }
        }
        return registro;
    }
    private String operaciones(String linea){
       
        String cod = "";
        String[] lista = linea.split(" ", 0);
        String op = lista[3];
        if(op.equals("+")){
            
            cod = construirOperacion("add ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals("-")){
            cod = construirOperacion("sub ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals("*")){
            
            cod += "mult "+ obtenerRegistro(lista[2]) + ", " +obtenerRegistro(lista[4]) +  "\n";
           
            liberarRegistro(lista[2]);
            liberarRegistro(lista[4]);
           
            cod += "mflo "+ guardarRegistros(lista[0]) + "\n";
            
            mul = true;
            return cod;
        }
        if(op.equals("/")){
            
            cod += "div "+ obtenerRegistro(lista[2]) + ", " + obtenerRegistro(lista[4]) +  "\n";
           
            liberarRegistro(lista[2]);
            liberarRegistro(lista[4]);
     
            cod += "mflo "+ guardarRegistros(lista[0])+ "\n";
            div = true;
            return cod;
        }
        if(op.equals("%")){
            cod += "div "+ obtenerRegistro(lista[2]) + ", " +obtenerRegistro(lista[4]) +  "\n";
            liberarRegistro(lista[2]);
            liberarRegistro(lista[4]);
           
            cod += "mfhi "+ guardarRegistros(lista[0])+ "\n";
            div = true;
            return cod;
        }
        if(op.equals("==")){
            cod = construirOperacion("seq ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals("<")){
            cod = construirOperacion("slt ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals(">")){
            cod = construirOperacion("sgt ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals(">=")){
            cod = construirOperacion("sge ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals("<=")){
            cod = construirOperacion("sle ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals("!=")){
            cod = construirOperacion("sne ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals("&")){
            cod = construirOperacion("and ",lista[0],lista[2],lista[4]);
            return cod;
        }
        if(op.equals("|")){
            cod = construirOperacion("or ",lista[0],lista[2],lista[4]);
            return cod;
        }
        return cod;
    }
    
     private String construirOperacion(String ins, String r1, String r2, String r3){
        String cod = "";
       
        cod = ins+ guardarRegistros(r1) +", " + obtenerRegistro(r2) + ", " +obtenerRegistro(r3) +  "\n";
        liberarRegistro(r2);
        liberarRegistro(r3);
        return cod;
    }
     
    public String obtenerRetorno(String linea){
        String cod = "";
        cod = construccionReturn(linea);
        eliminarScope();
        resetearRegistros();
        return cod;
    }
    
    private String construccionReturn(String linea){
        String cod = "";
        String[] lista = linea.split(" ", 0);
        cod += "add $sp, $sp, " + String.valueOf((pila.size()-1)*4 - ultimoRa*4) +"\n";
        cod += "lw $t0, 0($sp)\n";
        cod += "move $v0, "+obtenerRegistro(lista[1])+"\n";
        cod += "move $ra, $t0\n";
        cod += "add $sp, $sp, 4\n";
        cod += "jr $ra\n";
        return cod;
    }
    
    private void eliminarScope(){
        pila.subList(ultimoRa, pila.size()).clear();
        int i = 0;
        if(pila.size() != 0){
            for(i = pila.size()-1; pila.get(i).id.equals("ra"); i--){
                ultimoRa = pila.get(i).posicion;
            }
        }
    }
    
    public void resetearRegistros(){
        registros = new ArrayList<Cuadruplo>();
        cargarRegistrosT();
        rc = 0;
    }
    
    private void cargarRegistrosT(){
        Cuadruplo cuadru = new Cuadruplo();
        cuadru.argumento1 = "$t0";
        registros.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$t1";
        registros.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$t2";
        registros.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$t3";
        registros.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$t4";
        registros.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$t5";
        registros.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$t6";
        registros.add(cuadru);
        cuadru = new Cuadruplo();
        cuadru.argumento1 = "$t7";
        registros.add(cuadru);
    }
     private void cargarRegistrosA(){
        Cuadruplo r = new Cuadruplo();
        r.argumento1 = "$a0";
        arg.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$a1";
        arg.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$a2";
        arg.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$a3";
        arg.add(r);
    }
     private void cargarRegistrosS(){
        Cuadruplo r = new Cuadruplo();
        r.argumento1 = "$s0";
        registrosGuardados.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$s1";
        registrosGuardados.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$s2";
        registrosGuardados.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$s3";
        registrosGuardados.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$s4";
        registrosGuardados.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$s5";
        registrosGuardados.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$s6";
        registrosGuardados.add(r);
        r = new Cuadruplo();
        r.argumento1 = "$s7";
        registrosGuardados.add(r);
    }
    public String obtenerEstructura(String linea){
        String cod = "";
        String[] lista = linea.split(" ", 0);
        if(lista.length == 4){
            cod += "bne " +obtenerRegistro(lista[1])+", 1" + ", " + lista[3]+ "\n";
            liberarRegistro(lista[1]);
            return cod;
        }
        else{
            
            cod = "seq "+ guardarRegistros(lista[0]) +", " + obtenerRegistro(lista[1]) + ", " +obtenerRegistro(lista[3]) +  "\n";
            liberarRegistro(lista[1]);
            cod += "bne " +obtenerRegistro(lista[0])+", 1" + ", " + lista[5]+ "\n";
            liberarRegistro(lista[0]);
            return cod;
        }
    }
}
