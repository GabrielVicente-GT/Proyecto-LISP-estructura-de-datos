/***
 * Interfaz para la calculadora
 */
public interface CalculadoraGeneral {
    /**
     *
     * @param expresion String que contiene la operacion Postfix
     * @return Resultado o mensaje de no solucion
     */
    String Calculo(String expresion);
}