

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
String expresion = "(defun nombre (parametro) (* parametro parametro parametro))";
String expresion_2 = "(defun animal ( paro ) (* paro 2 2))";
        System.out.println("\n______________________-----------------------------------______________________");
        System.out.println("______________________----------LISP Interprete ------" +
                "" +
                "---______________________");
        System.out.println("______________________-----------------------------------______________________");

        boolean interprete = true;
        while(interprete ==  true){
            instruccion = scan.nextLine();
            System.out.println(esperanza.Calculo(instruccion));

        }

    }

}
