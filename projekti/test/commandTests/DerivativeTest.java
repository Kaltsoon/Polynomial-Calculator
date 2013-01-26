package commandTests;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import commands.Derivative;
/**
 *
 * @author Kalle
 */
public class DerivativeTest {
    Derivative derivative;
    public DerivativeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        derivative=new Derivative();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void erikoistapaukset(){
        derivative.handleParameters("derivative(x)");
        assertEquals("1", derivative.execute());
        derivative.handleParameters("derivative(1)");
        assertEquals("0", derivative.execute());
        derivative.handleParameters("derivative(0)");
        assertEquals("0", derivative.execute());
    }
    @Test
    public void derivaattoja(){
        derivative.handleParameters("derivative(3x^2+2x+1)");
        assertEquals("6x+2", derivative.execute());
        derivative.handleParameters("derivative(1/3x^4-5x^2-2/3x+1)");
        assertEquals("4/3x^3-10x-2/3", derivative.execute());
        derivative.handleParameters("derivative(x^3)");
        assertEquals("3x^2", derivative.execute());
        derivative.handleParameters("derivative(x+1)");
        assertEquals("1", derivative.execute());
    }
}
