import java.util.ArrayList;

public class Traductor {
    Calculadora cal=new Calculadora();
    ArrayList<Character> operaciones=new ArrayList<Character>();
    ArrayList<String> operaciones1=new ArrayList<String>();


    ArrayList<String> corroborrar=new ArrayList<String>();
    public int contarcaracteres(String cadena, char caracter) {
        int posicion, contador = 0;
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) {
            contador++;
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
    }
    public void operar(String codigo){
        String b=")";
        String c="(";
        char d='(';
        char a=')';
        int k=contarcaracteres(codigo,d);
        int h=contarcaracteres(codigo,a);
        StringBuilder palabara1= new StringBuilder(codigo);
        if(k==h){
            System.out.println("Se ingreso la operacion de buena manera");
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
    public String calcular(ArrayList<String> lista){
        String resultado="";
        System.out.println(lista);
        ArrayList<String> resul=new ArrayList<>();
        int c=lista.size();
        for(int i =0;i<c;i++){
            if(lista.get(i).equals("nulo")){
                break;
            }
            else{
                System.out.println(lista.get(i));
                if(lista.get(i).contains("(")){
                    String h=cal.Calculo(lista.get(i));
                    System.out.println(h);
                    String concatenado=lista.get(i+1)+" "+h;
                    lista.remove(i+1);
                    lista.add(i+1,concatenado);
                    System.out.println(concatenado);
                    System.out.println(lista);
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


}