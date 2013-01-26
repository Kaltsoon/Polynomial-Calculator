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
import commands.Multiply;
/**
 *
 * @author Kalle
 */
public class MultiplyTest {
    Multiply mul;
    public MultiplyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.mul=new Multiply();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void erikoistapaukset(){
        mul.handleParameters("multiply(x,0)");
        assertEquals("0", mul.execute());
        mul.handleParameters("multiply(x,1)");
        assertEquals("x", mul.execute());
        mul.handleParameters("multiply(1,1)");
        assertEquals("1", mul.execute());
        mul.handleParameters("multiply(x,-1)");
        assertEquals("-x", mul.execute());
        mul.handleParameters("multiply(x,x)");
        assertEquals("x^2", mul.execute());
        mul.handleParameters("multiply(x,2)");
        assertEquals("2x", mul.execute());
    }
    @Test
    public void tuloja(){
        mul.handleParameters("multiply(x^2+2x-5,1/2x^3-4/3x)");
        assertEquals("1/2x^5+x^4-23/6x^3-8/3x^2+20/3x", mul.execute());
        mul.handleParameters("multiply(x-1,x-1)");
        assertEquals("x^2-2x+1", mul.execute());
        mul.handleParameters("multiply(x^3+2x,1/3x-2/5)");
        assertEquals("1/3x^4-2/5x^3+2/3x^2-4/5x", mul.execute());
    }
}
