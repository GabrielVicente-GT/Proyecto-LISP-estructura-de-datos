

import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
public class Main {
    public static  void main(String[] args){
        /***
        *Instancia necesarias para que funcione el mini menu del usuario para saber que forma usar
        */

        Scanner scan = new Scanner(System.in);
        Calculadora_mega esperanza = new Calculadora_mega();
        String instruccion ="";
        System.out.println("\n______________________-----------------------------------______________________");
        System.out.println("______________________----------LISP Interprete ------" +
                "" +
                "---______________________");
        System.out.println("______________________-----------------------------------______________________");

        boolean interprete = true;
        while(interprete ==  true){
            instruccion = scan.nextLine();
            if (instruccion.equals("( exit )")) {
                interprete = false;
            }else{
                System.out.println(esperanza.Calculo(instruccion));
            }
            

        }

    }

}
