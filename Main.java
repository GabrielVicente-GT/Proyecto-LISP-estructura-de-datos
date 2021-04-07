/***
 * @author Gabriel Vicente \\ Pablo Gonzales \\ Javier Valle
 * Clase Main que tiene el método main iniciando el intérprete
 */
/***
*Se importa lo necesario para el funcionamiento del Main
*/
import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
public class Main {
    public static  void main(String[] args){
        /***
        *Instancia necesarias para que funcione el main
        */
        Calculadora_mega esperanza = new Calculadora_mega();
        
        Vector <String> varios = new Vector<String>();

        /***
         * try que lee el archivo.txt haciendo que cada linea se agregue al vector a modo de String
         */
        try{
            FileReader r = new FileReader("datos.txt");
            BufferedReader buffer = new BufferedReader(r);

            String temp ="";

            while(temp!=null){
                temp = buffer.readLine();
                if(temp == null){
                    break;
                }
                varios.add(temp);

            }
        }catch(Exception e){
            System.out.println("archivo no encontrado");
        }
        

        /***
         * Segun la cantidad de instrucciones en formato LISP que existan en el vector a cada una se le aplica la operación de
         * calculo de la clase Calculadora_mega devolviendo el resultado final.
         */
        /***
        * Se imprime todas las operaciones encontradas en el archivo txt
        */
        System.out.println();
        System.out.println("Operaciones archivo txt");
        System.out.println();
        for(int a =0;a<varios.size();a++){
            System.out.println(varios.get(a));
        }
        /***
        * Se imprimen los resultados de las instrucciones del txt
        */
        System.out.println();
        System.out.println("Resultados txt");
        System.out.println();
        for(int a =0;a<varios.size();a++){
            System.out.println(" "+esperanza.Calculo(varios.get(a)));
        }

        
        Scanner scan = new Scanner(System.in);
        
        String instruccion ="";
        System.out.println("\n______________________-----------------------------------______________________");
        System.out.println("______________________----------LISP Interprete ------" +
                "" +
                "---______________________");
        System.out.println("______________________-----------------------------------______________________");
        System.out.println();
        System.out.println();

        /***
        * Luego de leer el txt e imprimir los resultados, se da paso al inicio del interprete
        * dando opcion al usuario que ingrese instrucciones LISP
        * para finaliar el programa el usuario puede escribir la instruccion (exit)
        */

        boolean interprete = true;
        int numero = 0;
        while(interprete ==  true){
            numero++;
            System.out.print(" CL-USER "+numero+" > ");
            instruccion = scan.nextLine();
            if (instruccion.equals("( exit )")||instruccion.equals("(exit)")) {
                interprete = false;
            }else{
                try{
                    System.out.println(" "+esperanza.Calculo(instruccion));
                    System.out.println();
                }catch(Exception e){
                    System.out.println(" Error");
                    System.out.println();
                }
                
            }
            

        }
    }

}
