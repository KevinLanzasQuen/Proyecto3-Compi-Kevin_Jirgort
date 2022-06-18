/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciojflex;

/**
 *
 * @author Andrey McCarty
 */
public class EtiquetaRedir {
    private String inicio;
    private String fin;
    
    public EtiquetaRedir(String et1,String et2){
        inicio=et1;
        fin=et2;
    
    }
    public void inicio(String et1){
        inicio=et1;
    }
    public void fin(String et2){
        fin=et2;
    }
    public String getInicio(){
        return inicio;
    }
    public String getFin(){
        return fin;
    }
}

