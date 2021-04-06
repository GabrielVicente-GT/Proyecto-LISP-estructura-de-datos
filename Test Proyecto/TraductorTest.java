import junit.framework.TestCase;

import java.io.FileNotFoundException;

public class TraductorTest extends TestCase {
    archivos arch=new archivos();
    Traductor t=new Traductor();


    public void testCalcular() throws FileNotFoundException {
        String a=arch.leer("/Users/antonio/Desktop/Desktop/Defun/Archivo.txt");
        t.operar(a);
        assertEquals(" 1000.0",t.calcular(t.regresarArray()));
    }


}