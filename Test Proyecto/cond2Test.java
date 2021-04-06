import junit.framework.TestCase;

public class cond2Test extends TestCase {
    cond2 c=new cond2();

    public void testRegresarArray() {
        String h="(cond (( = 1 1) + 3 4 5 ) (t ( * n ( facial ( n-1))))) ";
        c.setarrays(h);
        assertEquals("( + 3 4 5 ",c.regresarcodigo());
    }


}