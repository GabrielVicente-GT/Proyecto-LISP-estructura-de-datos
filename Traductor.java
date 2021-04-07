/***
 * @author Gabriel Vicente \\ Pablo Gonzales \\ Javier Valle
 * Clase Traductor que implementa la calculadora.java para realizar las operaciones mas complejas
 */
import java.util.ArrayList;

public class Traductor {
    Calculadora cal=new Calculadora();
    ArrayList<Character> operaciones=new ArrayList<Character>();
    ArrayList<String> operaciones1=new ArrayList<String>();
    ArrayList<String> corroborrar=new ArrayList<String>();
    /***
     *
     * @param cadena String que contiene la instruccion LISP a resolver
     * @param caracter posicion de un caracter
     * @return resultado de la operacion
     */
    public int contarcaracteres(String cadena, char caracter) {
        int posicion, contador = 0;
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) {
            contador++;
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
    }/***
     *
     * @param codigo String con la instruccion completa
     */
    public void operar(String codigo){
        String b=")";
        String c="(";
        char d='(';
        char a=')';
        int k=contarcaracteres(codigo,d);
        int h=contarcaracteres(codigo,a);
        StringBuilder palabara1= new StringBuilder(codigo);
        if(k==h){
            
            for(int i=0;i<k;i++){

                int num4=palabara1.lastIndexOf(c);
                int num3=palabara1.indexOf(b,num4);
                String x=palabara1.substring(num4,num3);
                palabara1.delete(num4,num3);
                operaciones1.add(x);


            }

        }
        else{
            System.out.println("Existe un error de sintaxis");
        }
        operaciones1.add("");




    }
    /***
     *
     * @param lista Arraylist a partir de la string
     * @return resultado de la operacion
     */
    public String calcular(ArrayList<String> lista){
        String resultado="";
        ArrayList<String> resul=new ArrayList<>();
        int c=lista.size();
        for(int i =0;i<c;i++){
            if(lista.get(i).equals("nulo")){
                break;
            }
            else{
                if(lista.get(i).contains("(")){
                    String h=cal.Calculo(lista.get(i));

                    String concatenado=lista.get(i+1)+" "+h;
                    lista.remove(i+1);
                    lista.add(i+1,concatenado);
                    
                }






            }

        }
        return lista.get(lista.size()-1) ;

    }
    public void realizar(String signo){

    }
    public  ArrayList<String> regresarArray(){
        return operaciones1;
    }
    public void limpiando (){
        operaciones1.clear();
    }


}