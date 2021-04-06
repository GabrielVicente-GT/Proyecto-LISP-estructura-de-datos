/**
 * Clase Calculador aque implementa Calculadora General para ser fiel a lo acordado con la clase
 */
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Set;
public class Calculadora_mega implements CalculadoraGeneral{
    /***
     * instancia del ArrayList que funciona como la pila mencionada en la hoja.
     */
    ArrayList<String> funciones = new ArrayList<String>();
    HashMap<String,String> variables =  new HashMap<String, String>();
    ArrayList<HashMap> parametros_instrucciones = new ArrayList<HashMap>();
    Calculadora calculadora = new Calculadora();
    Traductor t = new Traductor();
    QUOTE q =  new QUOTE();
    COND c =  new COND();
    
    /***
     *
     * @param expresion String que contiene la operación Postfix que se desea resolver
     * @return resultado de la operacion Postfix o mensaje de que la operacion no es valida
     */
    public String Calculo(String expresion){
        String str1 = "";
        String operacion = "";
        
        for (int i = 0; i < expresion.length(); i++){
            if(String.valueOf(expresion.charAt(i)).equals(")")){
                str1 = str1 +" " +expresion.charAt(i);
            }
            else if(String.valueOf(expresion.charAt(i)).equals("(")){
                str1 = str1 +expresion.charAt(i)+" ";
            }else{
                str1 = str1 + expresion.charAt(i);
            }
        }
        /*
        System.out.println(str1);*/
        String[] str2 = str1.split(" ");

        ArrayList<String> Cadena = new ArrayList<String>();

        for (int n = 0; n <str2.length; n++) { 
             
            Cadena.add(String.valueOf(str2[n]));
        }

        for (int i = 0; i < Cadena.size(); i++){
            if(Cadena.get(i).equals("")){
                Cadena.remove(i);
            }  
        }
        /*
        for (int i = 0;i<Cadena.size() ;i++ ) {
            System.out.println(Cadena.get(i));
        }*/
/*
        Collections.reverse(Cadena);
*/
        

        /***
        Inicio de mega calculadora
        ***/
        //comprobante de funcion llamada
        boolean comprobante_de_funcion = false;
        int posicionamiento = 0;
        for (int i = 0;i<funciones.size() ; i++) {
            if (Cadena.get(1).equals(funciones.get(i))) {
                comprobante_de_funcion = true;
                posicionamiento = i;
            }
            
        }
        //comprobante de variable llamada
        String llavesotota = "";
        Set<String> llaves_variables = variables.keySet();
        boolean comprobante_de_variable = false;

        for (String ii : llaves_variables) {
            if(Cadena.get(1).equals(ii)){
                llavesotota = ii;
                comprobante_de_variable = true;
            }
        }
        
        
        String expresion_operada = " ";
        for (int i = 0;i<Cadena.size() ;i++ ) {
            expresion_operada = expresion_operada + Cadena.get(i)+ " ";
        }

        //inician condicionales para operaciones de lisp

        if(Cadena.get(1).equals("defun")){
            //comprobante de variable en operacion
        String llave_obtenida= "";
        Set<String> llaves_comprobantes = variables.keySet();

        for (String ii : llaves_comprobantes) {
            for (int i = 0;i<Cadena.size() ;i++ ) {
                if(Cadena.get(i).equals(ii)){
                    Cadena.set(i,variables.get(ii));
                }
            }
        }
            Cadena.remove(1);
            operacion = "";

            funciones.add(Cadena.get(1));
            Cadena.remove(1);
            
            String llave = Cadena.get(2);
            Cadena.remove(2);
            Cadena.remove(2);
            Cadena.remove(1);

            String valor = " ";
            for (int i = 0;i<Cadena.size() ; i++) {
                valor = valor + Cadena.get(i)+" ";
            }
            HashMap<String,String> hashito =  new HashMap<String, String>();
            hashito.put(llave,valor);
            parametros_instrucciones.add(hashito);
       
        }
        else if(Cadena.get(1).equals("*")||Cadena.get(1).equals("/")||Cadena.get(1).equals("-")||Cadena.get(1).equals("+")){
            //comprobante de variable en operacion
            
            String llave_obtenida= "";
            Set<String> llaves_comprobantes = variables.keySet();

            for (String ii : llaves_comprobantes) {
                for (int i = 0;i<Cadena.size() ;i++ ) {
                    if(Cadena.get(i).equals(ii)){
                        Cadena.set(i,variables.get(ii));
                    }
                }
            }
            t.operar(expresion_operada);
            operacion = t.calcular(t.regresarArray());
        }
        else if(comprobante_de_funcion == true){
            String llave_obtenida= "";
            Set<String> llaves_comprobantes = variables.keySet();

            for (String ii : llaves_comprobantes) {
                for (int i = 0;i<Cadena.size() ;i++ ) {
                 if(Cadena.get(i).equals(ii)){
                     Cadena.set(i,variables.get(ii));
                    }
                }
            }
            Set<String> llaven = parametros_instrucciones.get(posicionamiento).keySet();
            Collection<String> llavon = parametros_instrucciones.get(posicionamiento).values();
            String lavesita = "";
            String valorcito = "";
            for (String i : llaven ) {
                lavesita = i;
            }
            for (String i : llavon ) {
                valorcito = i;
            }
            String[] str22 = valorcito.split(" ");

            ArrayList<String> Cadenaa = new ArrayList<String>();

            for (int n = 0; n <str22.length; n++) { 
             
                Cadenaa.add(String.valueOf(str22[n]));
            }
        
            for (int i = 0; i < Cadenaa.size(); i++){
                if(Cadenaa.get(i).equals(lavesita)){
                    Cadenaa.set(i,Cadena.get(2));
                }
            }
            String valor_operar = " ";
            for (int i = 0;i<Cadenaa.size() ; i++) {
                valor_operar = valor_operar + Cadenaa.get(i)+" ";
            }
            System.out.println(valor_operar);
            t.operar(valor_operar);
            operacion = t.calcular(t.regresarArray());
            comprobante_de_funcion = false;

        }else if(Cadena.get(1).equals("setq")&& Cadena.size()==5){
            if(variables.containsKey(Cadena.get(2))){
                System.out.println();
            }else{
                variables.put(Cadena.get(2),Cadena.get(3));
                operacion = Cadena.get(3);
                /*
                System.out.println(variables);
                */
            }
            
        }else if (comprobante_de_variable == true) {
            operacion = variables.get(llavesotota);
            llavesotota = "";
            comprobante_de_variable = false;
        }else if(Cadena.get(1).equals("quote")){
            operacion =q.quote(expresion);
        }else if(Cadena.get(1).equals("cond")){
        

            Cadena.remove(1);
            String condicionando = "";
            for (int i = 0;i<Cadena.size() ;i++ ) {
                condicionando =  condicionando +Cadena.get(i)+" ";
            }
            operacion =c.Calculo(condicionando);
        }

        return operacion;
    }
}