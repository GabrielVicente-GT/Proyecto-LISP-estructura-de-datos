/***
 * @author Gabriel Vicente \\ Pablo Gonzales \\ Javier Valle
 * Clase Calculadora_mega que contiene la funcion de Calculo que permite analizar una instruccion LISP
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
     * Instancias necesarias para que la Calculadora_mega funcione. Van desde objetos de otras clases a estructuras.
     */
    ArrayList<String> funciones = new ArrayList<String>();
    HashMap<String,String> variables =  new HashMap<String, String>();
    ArrayList<HashMap> parametros_instrucciones = new ArrayList<HashMap>();
    Calculadora calculadora = new Calculadora();
    Traductor t = new Traductor();
    QUOTE q =  new QUOTE();
    cond2 c = new cond2();
    Defuning d =  new Defuning();
    
    /***
     *
     * @param expresion String que contiene la instruccion LISP a resolver
     * @return resultado de la operacion
     */
    public String Calculo(String expresion){
        /***
        * La expresion se convierte en algo más facil de leer y ordenador de tal manera que su analisis sea más factible.
        */
        c.cleararrays();
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

       /***
        * Aqui finaliza el refinamiento de la instruccion e inicia el analisis de las funciones
        * que se pueden hacer con el interprete
        */


        /***
        * Se verifica si la instruccion llama a una funcion, y si ese es el caso se almacena
        * que funcion es y el comprobante de la funcion se convierte en verdadero
        */
        boolean comprobante_de_funcion = false;
        int posicionamiento = 0;
        for (int i = 0;i<funciones.size() ; i++) {
            if (Cadena.get(1).equals(funciones.get(i))) {
                comprobante_de_funcion = true;
                posicionamiento = i;
            }
            
        }
        /***
        * Se verifica que existe el llamado de alguna variable para saber su valor.
        */
        String llavesotota = "";
        Set<String> llaves_variables = variables.keySet();
        boolean comprobante_de_variable = false;

        for (String ii : llaves_variables) {
            if(Cadena.get(1).equals(ii)){
                llavesotota = ii;
                comprobante_de_variable = true;
            }
        }
        /***
        * si la instruccion es un defun se almacena lo necesario y se devuelve el nombre de la funcion almacenda
        */
        if(Cadena.get(1).equals("defun")){


            /*Si existe alguna variable en el defun, se sustituye directamente el valor */
            String llave_obtenida= "";
            Set<String> llaves_comprobantes = variables.keySet();

            for (String ii : llaves_comprobantes) {
                for (int i = 0;i<Cadena.size() ;i++ ) {
                    if(Cadena.get(i).equals(ii)){
                        Cadena.set(i,variables.get(ii));
                    }
                }
            }
            /*Se remueve la palabra defun en ela instruccion*/
            Cadena.remove(1);
            operacion = "";

            /*Se agrega el nombre de la funcion y se remueve de la instruccion*/
            operacion = Cadena.get(1);
            funciones.add(Cadena.get(1));
            Cadena.remove(1);
            
            /*Se remueven los parentesis y valores que no se necesitan y se guarda la llave*/

            String llave = Cadena.get(2);
            Cadena.remove(2);
            Cadena.remove(2);
            Cadena.remove(1);
            Cadena.remove(0);
            Cadena.remove(Cadena.size()-1);

            /*Lo que quede de la instruccion corresponde a la funcion que se desea guardar, se concatena y se guarda en el arraylist de Hashmaps*/

            String valor = " ";
            for (int i = 0;i<Cadena.size() ; i++) {
                valor = valor + Cadena.get(i)+" ";
            }
            HashMap<String,String> hashito =  new HashMap<String, String>();
            hashito.put(llave,valor);
            parametros_instrucciones.add(hashito);
       
        }

        /**
        * se detecto una funcion conocida y se hara la operacion de la instruccion que se ha guardado con el nombre de esa funcion
        **/
        
        else if(comprobante_de_funcion == true){

        	/***
        	* Se sustituye el valor de alguna variable si es que se usa
        	***/
            String llave_obtenida= "";
            Set<String> llaves_comprobantes = variables.keySet();

            for (String ii : llaves_comprobantes) {
                for (int i = 0;i<Cadena.size() ;i++ ) {
                 if(Cadena.get(i).equals(ii)){
                     Cadena.set(i,variables.get(ii));
                    }
                }
            }

            /***
            * Se obtiene el parametro y la instruccion de la funcion llamada y se guarda el lavesita y valorcito, parametro e instruccion
            ***/

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

            /*Se procesa la instruccion almacenada en la funcion en str 22 y se separa por espacios ya que es una instruccion modelada de mejor forma*/


            String[] str22 = valorcito.split(" ");

            ArrayList<String> Cadenaa = new ArrayList<String>();

            /*str22 se convierte en cadenaa y contendra cada uno de los valores*/

            for (int n = 0; n <str22.length; n++) { 
             
                Cadenaa.add(String.valueOf(str22[n]));
            }

           

            for (int i = 0;i<Cadenaa.size() ; i++) {
                if(Cadenaa.get(i).equals("")){
                    Cadenaa.remove(i);
                }
            }

            String valor_operar = "";

            /***
            *Si la funcion contiene un cond se toma como si fuera recursiva y se hace el respectivo procedimiento
            */
            if(Cadenaa.get(1).equals("cond")){
                
            	for (int i = 0;i<Cadenaa.size() ; i++) {
                	valor_operar = valor_operar + Cadenaa.get(i)+" ";
            	}
            
            	operacion = d.recursivo(valor_operar,Cadena.get(2),lavesita,funciones.get(posicionamiento));
            /***
            * Si la instruccion llamada no tiene cond se deduce que no es recursiva y se procede a resolverla
            */
            }else{
                for (int i = 0; i < Cadenaa.size(); i++){
                	if(Cadenaa.get(i).equals(lavesita)){
                    	Cadenaa.set(i,Cadena.get(2));
                	}
            	}
                for (int i = 0;i<Cadenaa.size() ; i++) {
                	valor_operar = valor_operar + Cadenaa.get(i)+" ";
            	}
                t.operar(valor_operar);
                operacion = t.calcular(t.regresarArray()); 
            }
            /***
            *se restaura el valor
            */
            comprobante_de_funcion = false;
        /***
        *si la instruccion corresponde a setq se guarda el valor y la variable en el hashmap
        */
        }else if(Cadena.get(1).equals("setq")&& Cadena.size()==5){
            if(variables.containsKey(Cadena.get(2))){
            	variables.remove(Cadena.get(2));
            	variables.put(Cadena.get(2),Cadena.get(3));
                operacion = Cadena.get(3);

            }else{
                variables.put(Cadena.get(2),Cadena.get(3));
                operacion = Cadena.get(3);
            }
        /***
        *Se devuelve el valor de la variable llamada
        */
        }else if (comprobante_de_variable == true) {
            operacion = variables.get(llavesotota);
            llavesotota = "";
            comprobante_de_variable = false;
        /***
        * Si la instruccion es quote se llama a QUOTE para que realice la operacion
        */
        }else if(Cadena.get(1).equals("quote")){
            operacion =q.quote(expresion);
        /***
        * Si es cond se aplica lo necesario para resolver segun la condicion, en esta parte no se puede aplicar recursividad.
        */
        }else if(Cadena.get(1).equals("cond")){
        	/***
        	* Se sustituye el valor de alguna variable si es que se usa
        	***/
            String llave_obtenida= "";
            Set<String> llaves_comprobantes = variables.keySet();

            for (String ii : llaves_comprobantes) {
                for (int i = 0;i<Cadena.size() ;i++ ) {
                    if(Cadena.get(i).equals(ii)){
                        Cadena.set(i,variables.get(ii));
                    }
                }
            }
            /*concatenacion de la string que se puede operar*/
        	String expresion_operada = " ";
            for (int i = 0;i<Cadena.size() ;i++ ) {
                expresion_operada = expresion_operada + Cadena.get(i)+ " ";
            }

            c.setarrays(expresion_operada);

            String paso_que_voy_ardiendo = c.regresarcodigo();
            String valoraso = c.regresarestado();
            if(valoraso.equals("v")){
                t.operar(paso_que_voy_ardiendo);
                operacion = t.calcular(t.regresarArray());
            }else{
                t.operar(paso_que_voy_ardiendo);
                operacion = t.calcular(t.regresarArray());
            }
            
      

            c.cleararrays();
        /***
        * aqui inicia el las demas instrucciones que se solicitan en LISP
        */
        }else if(Cadena.get(1).equals("equals")||Cadena.get(1).equals("equal")){
            String llave_obtenida= "";
            Set<String> llaves_comprobantes = variables.keySet();

            for (String ii : llaves_comprobantes) {
                for (int i = 0;i<Cadena.size() ;i++ ) {
                 if(Cadena.get(i).equals(ii)){
                     Cadena.set(i,variables.get(ii));
                    }
                }
            }
            if(Cadena.get(2).equals(Cadena.get(3))){
                operacion = " T";
            }else{
                operacion = " NIL";
            }
        }else if(Cadena.get(1).equals("atom")){
            if (Cadena.size()==4) {
                operacion = " T";
            }else{
                operacion = " NIL";
            }
        }else if(Cadena.get(1).equals("list")){
            String llave_obtenida= "";
            Set<String> llaves_comprobantes = variables.keySet();

            for (String ii : llaves_comprobantes) {
                for (int i = 0;i<Cadena.size() ;i++ ) {
                    if(Cadena.get(i).equals(ii)){
                        Cadena.set(i,variables.get(ii));
                    }
                }
            }
            Cadena.remove(0);
            Cadena.remove(0);
            Cadena.remove(Cadena.size()-1);
            String niteal = "";
            for (int num = 0;num<Cadena.size();num++) {
                niteal = niteal + Cadena.get(num)+" ";
            }
            operacion = niteal;
        /***
        * si no es ninguna de las demas instrucciones se asume que es una aritmetica y se llama a la calculadora aritmetica completa para que realice la instruccion
        */
        }else if (Cadena.get(1).equals("*") || Cadena.get(1).equals("-") || Cadena.get(1).equals("+") || Cadena.get(1).equals("/")|| Cadena.get(1).equals("<")|| Cadena.get(1).equals(">")){
            /***
        	* Se sustituye el valor de alguna variable si es que se usa
        	***/
            String llave_obtenida= "";
            Set<String> llaves_comprobantes = variables.keySet();

            for (String ii : llaves_comprobantes) {
                for (int i = 0;i<Cadena.size() ;i++ ) {
                    if(Cadena.get(i).equals(ii)){
                        Cadena.set(i,variables.get(ii));
                    }
                }
            }
            /*concatencion de la String la cual se puede operar*/
            String expresion_operada = " ";
            for (int i = 0;i<Cadena.size() ;i++ ) {
                expresion_operada = expresion_operada + Cadena.get(i)+ " ";
            }

            /*Uso de la calculadora compleja*/

            t.operar(expresion_operada);
            operacion = t.calcular(t.regresarArray());
        }else{
            operacion = "";
        }
        t.limpiando();
        return operacion;
    }
}