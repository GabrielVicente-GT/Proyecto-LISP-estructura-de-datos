/***
 * @author Gabriel Vicente \\ Pablo Gonzales \\ Javier Valle
 * Clase Main que tiene el método main iniciando el intérprete
 */
import java.util.Vector;

/**
 *
 * @param <T> clase StackVector que implementa Stack
 */
public class StackVector<T> implements Stack<T>{
    /***
     * se crea un vector con el generico para que el desarrollador decida que hacer con el
     */

    Vector <T> stack = new Vector<T>();

    /**
     *
     * @param num equivalente a agregar un elemento al vector
     */
    @Override
    public void push(T num) {
        stack.add(num);
    }

    /***
     *
     * @return esta vacio true no esta vacio false
     */
    @Override
    public boolean empty() {
        boolean cantidad = false;
        if (stack.size()==0){
            cantidad = true;
        }
        return cantidad;
    }

    /***
     *
     * @return cantidad en el vector
     */
    @Override
    public int size() {
        return stack.size();
    }

    /**
     *
     * @return quita ultimo valor del vector no sin antes regresarlo cono return
     */
    @Override
    public T pop() {
        T num = peek();
        stack.removeElementAt(stack.size()-1);
        return num;
    }

    /***
     *
     * @return regresa el ultimo valor del vector sin quitarlo.
     */
    @Override
    public T peek() {
        return stack.get(stack.size()-1);
    }

    public void limp(){
        stack.clear();
    }
}