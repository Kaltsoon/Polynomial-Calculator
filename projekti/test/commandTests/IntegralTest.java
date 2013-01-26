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
import commands.Integral;
/**
 *
 * @author Kalle
 */
public class IntegralTest {
    private Integral integral;
    public IntegralTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        integral = new Integral();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void maarattyjaIntegraaleja(){
         integral.handleParameters("integral(-1,1,1)");
         assertEquals("2 (&asymp;2.0)", integral.execute());
         integral.handleParameters("integral(1,1,1)");
         assertEquals(" (&asymp;0.0)", integral.execute());
         integral.handleParameters("integral(0,7,4x^2-5x+11)");
         assertEquals("2471/6 (&asymp;411.8333333333333)", integral.execute());
    }
    @Test
    public void maarittelemattomiaIntegraaleja(){
         integral.handleParameters("integral(x)");
         assertEquals("1/2x^2", integral.execute());
         integral.handleParameters("integral(-1/5x^3+2x^2-4x+11)");
         assertEquals("-1/20x^4+2/3x^3-2x^2+11x", integral.execute());
         integral.handleParameters("integral(5x^2-2x+1)");
         assertEquals("5/3x^3-x^2+x", integral.execute());
    }
}
