/**
 * Clase Calculador aque implementa Calculadora General para ser fiel a lo acordado con la clase
 */

import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
public class Defuning{
    /***
     * instancia del StackVector que funciona como la pila mencionada en la hoja.
     */
    cond2 c = new cond2();
    Traductor t = new Traductor();
    ArrayList<Float> valores = new ArrayList<Float>();
    /***
     *
     * @param expresion String que contiene la operación Postfix que se desea resolver
     * 
     */
    public String recursivo(String exp,String valor,String parametro,String nombre){
        System.out.println(exp);
        System.out.println(valor);
        System.out.println(parametro);
        System.out.println(nombre);
        System.out.println(exp.length());
        String finale = "";
        boolean tipo = false;

        
        /*condicion para el valor de 0 para evitar especificaciones en el cond*/
        if (exp.length()<63) {
            valores.add(2.0f);
            tipo = false;
        }else if (exp.length()>63) {
            valores.add(0.0f);
            tipo = true;
        }
        /*
        impresion de lo que hay e valores
        */
        System.out.println("Lo que hay en valores");
        for (int n = 0;n<valores.size() ; n++) {
            System.out.println(valores.get(n));
        }

        /*empieza recursividad*/
        int actual = 1;
        String operacion = "";
        while(actual<=Integer.parseInt(valor)){
            /*se instancian varias cadenas para reanudar el valor*/
            String[] paso = exp.split(" ");
            

            ArrayList<String> Cadena = new ArrayList<String>();
            for (int n = 0; n <paso.length; n++) { 
                Cadena.add(String.valueOf(paso[n]));
            }
            /*se sustituye el parametro por el valor evaluado actual*/
            for (int i = 0; i < Cadena.size(); i++){
                if(Cadena.get(i).equals(parametro)){
                    Cadena.set(i,actual+"");
                }
            }/*impresion de cadena*//*
            for (int i  = 0;i<Cadena.size() ;i++ ) {
                System.out.println(Cadena.get(i));
            }*/
            /*cadena concatenada*/
            String valor_operar="";
            for (int i = 0;i<Cadena.size() ; i++) {
                valor_operar = valor_operar + Cadena.get(i)+" ";
            }
            /*como se encuentra el rray ahorita*/
            System.out.println(valor_operar);
            

            /*funciones para el COND*/
            c.setarrays(valor_operar);
            String paso_que_voy_ardiendo = c.regresarcodigo();
            String valoraso = c.regresarestado();

            /*Si la condicion da true se opera*/
            if(valoraso.equals("v")){
                t.operar(paso_que_voy_ardiendo);
                operacion = t.calcular(t.regresarArray());
                valores.add(Float.parseFloat(operacion));
                System.out.println(paso_que_voy_ardiendo);
            }
            /*si la condicion es falsa paso_que_voy_ardiendo tiene que hacer otros procesos*/
            else{
                String signo = "";
                String posicion_1 = "";
                String posicion_2 = "";
                String finalmente = "";
                System.out.println(paso_que_voy_ardiendo);
                String[] avenida = paso_que_voy_ardiendo.split(" ");
                ArrayList<String> casi = new ArrayList<String>();
                for (int n = 0; n <avenida.length; n++) {
                    if (avenida[n].equals(nombre)||avenida[n].equals("(")||avenida[n].equals(")")) {
                        
                    }else{
                        casi.add(String.valueOf(avenida[n]));
                    } 
                }

                /*impresion en lo que hay del Arraylist*/
                for (int i  = 0;i<casi.size() ;i++ ) {
                    System.out.println(casi.get(i));
                }
                signo = casi.get(0);
                casi.remove(0);
                
                if (tipo==false) {
                    posicion_1 = casi.get(0);
                    casi.remove(0);
                    posicion_2 = casi.get(0)+" "+casi.get(1)+" "+casi.get(2);
                    casi.clear();
                    System.out.println("---------");
                    System.out.println(casi.size());
                    System.out.println("signo "+signo);
                    System.out.println("posicion 1 "+posicion_1);
                    System.out.println("posicion 2 "+posicion_2);
                    posicion_2 = "("+posicion_2+")";
                    t.operar(posicion_2);
                    posicion_2 = t.calcular(t.regresarArray());
                    System.out.println("posicion 2 operada: "+posicion_2);
                    posicion_2 = valores.get(Math.round(Float.parseFloat(posicion_2)))+"";
                    System.out.println("posicion 2 operada_2: "+posicion_2);

                    finalmente = "("+signo+" "+posicion_1+" "+posicion_2+")"; 
                    System.out.println(finalmente);
                    t.operar(finalmente);
                    finalmente = t.calcular(t.regresarArray());    
                    valores.add(Float.parseFloat(finalmente));     

                    /*operacion de agregar al stack*/
                }else{
                    posicion_1 = casi.get(0)+" "+casi.get(1)+" "+casi.get(2);
                    casi.remove(0);
                    casi.remove(0);
                    casi.remove(0);
                    posicion_2 = casi.get(0)+" "+casi.get(1)+" "+casi.get(2);
                    casi.clear();
                    System.out.println("---------");
                    System.out.println(casi.size());
                    System.out.println("signo "+signo);
                    System.out.println("posicion 1 "+posicion_1);
                    System.out.println("posicion 2 "+posicion_2);
                    posicion_1 = "("+posicion_1+")";
                    t.operar(posicion_1);
                    posicion_1 = t.calcular(t.regresarArray());
                    System.out.println("posicion 1 operada: "+posicion_1);
                    posicion_1 = valores.get(Math.round(Float.parseFloat(posicion_1)))+"";
                    System.out.println("posicion 1 operada_2: "+posicion_1);

                    posicion_2 = "("+posicion_2+")";
                    t.operar(posicion_2);
                    posicion_2 = t.calcular(t.regresarArray());
                    System.out.println("posicion 2 operada: "+posicion_2);
                    posicion_2 = valores.get(Math.round(Float.parseFloat(posicion_2)))+"";
                    System.out.println("posicion 2 operada_2: "+posicion_2);

                    finalmente = "("+signo+" "+posicion_1+" "+posicion_2+")"; 
                    System.out.println(finalmente);
                    t.operar(finalmente);
                    finalmente = t.calcular(t.regresarArray());    
                    valores.add(Float.parseFloat(finalmente));

                }
                System.out.println("-------------------");
                for (int i  = 0;i<casi.size() ;i++ ) {
                    System.out.println(casi.get(i));
                }

            }

            c.cleararrays();
            actual++;
            
        }

        finale =  valores.get(valores.size()-1)+" ";

        /*se limpian los valores*/
        /*
        impresion de lo que hay e valores
        */
        System.out.println("Lo que hay en valores");
        for (int n = 0;n<valores.size() ; n++) {
            System.out.println(valores.get(n));
        }
        System.out.println("------------------------");
        System.out.println("Respuesta final");
        System.out.println("------------------------");
        valores.clear();

        return finale;
    }
}