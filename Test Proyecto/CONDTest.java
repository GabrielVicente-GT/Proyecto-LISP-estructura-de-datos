import junit.framework.TestCase;

public class CONDTest extends TestCase {
    COND c=new COND();
    public void testCalculo() {
        assertEquals(false,c.Calculo("( = 1 2)"));
    }
}