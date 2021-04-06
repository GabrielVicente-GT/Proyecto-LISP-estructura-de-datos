import junit.framework.TestCase;

public class CalculadoraTest extends TestCase {
    Calculadora c=new Calculadora();
    public void testCalculo() {
        assertEquals("2.0",c.Calculo("(+ 1 1)"));
    }
}
