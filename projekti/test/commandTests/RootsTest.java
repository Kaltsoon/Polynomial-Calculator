/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commandTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import commands.Roots;
/**
 *
 * @author Kalle
 */
public class RootsTest {
    Roots roots;
    public RootsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.roots=new Roots();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void tarkkojaArvoja(){
       roots.handleParameters("roots(2x+1)");
       assertEquals("x=-1/2", roots.execute());
       roots.handleParameters("roots(1/2x^2+2x-3/4)");
       assertEquals("x=(-2-SQRT(11/2))/(1), or x=(-2+SQRT(11/2))/(1)", roots.execute());
       roots.handleParameters("roots(x^2+2x+1)");
       assertEquals("x=-1", roots.execute()); 
       roots.handleParameters("roots(-3x^3+x^2-4x)");
       assertEquals("x=0", roots.execute()); 
    }
    @Test
    public void likiArvoja(){
       roots.handleParameters("roots(4x^4+1/5x^3-9x^2+x-2/3)");
       assertEquals("x=-1.597, or x=1.444", roots.execute());
       roots.handleParameters("roots(x^3+2x^2-12)");
       assertEquals("x=1.781", roots.execute());
    }
}
