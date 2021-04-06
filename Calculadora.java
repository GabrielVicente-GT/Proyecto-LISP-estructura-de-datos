/**
 * Clase Calculador aque implementa Calculadora General para ser fiel a lo acordado con la clase
 */

import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
public class Calculadora implements CalculadoraGeneral{
    /***
     * instancia del StackVector que funciona como la pila mencionada en la hoja.
     */
    StackVector<Float> stack = new StackVector<Float>();

    /***
     *
     * @param expresion String que contiene la operación Postfix que se desea resolver
     * @return resultado de la operacion Postfix o mensaje de que la operacion no es valida
     */
    public String Calculo(String expresion){
        String str1 = "";



        for (int i = 0; i < expresion.length(); i++){
            if(String.valueOf(expresion.charAt(i)).equals(")")||String.valueOf(expresion.charAt(i)).equals("(")){

            }else{
                str1 = str1 + expresion.charAt(i);
            }
        }
/*
        System.out.println(expresion);
        System.out.println(str1);*/
        String[] str2 = str1.split(" ");

        ArrayList<String> CadenaInvertida = new ArrayList<String>();

        for (int n = 0; n <str2.length; n++) {

            CadenaInvertida.add(String.valueOf(str2[n]));
        }

        for (int i = 0; i < CadenaInvertida.size(); i++){
            if(CadenaInvertida.get(i).equals("")){
                CadenaInvertida.remove(i);
            }
        }


        Collections.reverse(CadenaInvertida);



        String operacionando = CadenaInvertida.get(CadenaInvertida.size()-1);
        float cantidades = CadenaInvertida.size()-2;


        if(operacionando.equals("+")){
            for (int i =1;i<cantidades ;i++ ) {
                CadenaInvertida.add("+");
            }
        }else if(operacionando.equals("-")){
            for (int i =1;i<cantidades ;i++ ) {
                CadenaInvertida.add("-");
            }
        }else if(operacionando.equals("/")){
            for (int i =1;i<cantidades ;i++ ) {
                CadenaInvertida.add("/");
            }
        }else if(operacionando.equals("*")){
            for (int i =1;i<cantidades ;i++ ) {
                CadenaInvertida.add("*");
            }
        }


        String operacion = "";
        boolean com = true;
        String[] SplitOperation;
        float operadorB =0;
        float operadorA =0;
        float nuevo =0;
        SplitOperation = expresion.split(" ");

        for (int i = 0; i < CadenaInvertida.size(); i++){
            if(CadenaInvertida.get(i).equals("*") || CadenaInvertida.get(i).equals("-") || CadenaInvertida.get(i).equals("+") || CadenaInvertida.get(i).equals("/")){
                if(stack.size()>=2){
                    operadorA = stack.pop();
                    operadorB = stack.pop();
                    if(CadenaInvertida.get(i).equals("*")){
                        nuevo = operadorA*operadorB;
                    }else if(CadenaInvertida.get(i).equals("/")){
                        nuevo = operadorA/operadorB;
                    }else if(CadenaInvertida.get(i).equals("+")){
                        nuevo = operadorA+operadorB;
                    }else if(CadenaInvertida.get(i).equals("-")){
                        nuevo = operadorA-operadorB;
                    }
                    stack.push(nuevo);
                }else{
                    com = false;
                }


            }else if(CadenaInvertida.get(i).equals("")){

            }
            else{
                float num = Float.parseFloat(CadenaInvertida.get(i));
                stack.push(num);
            }
        }
        if(com == true){
            operacion = Float.toString(stack.pop());
        }else if(com == false){
            operacion = "No es posible operar";
        }


        return operacion;
    }
}