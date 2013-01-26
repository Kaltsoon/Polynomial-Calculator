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
import commands.Divide;
/**
 *
 * @author Kalle
 */
public class DivideTest {
    Divide divide;
    public DivideTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        divide=new Divide();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void erikoistapaukset(){
        divide.handleParameters("divide(x,1)");
        assertEquals("x", divide.execute());
        divide.handleParameters("divide(x,1/3)");
        assertEquals("3x", divide.execute());
        divide.handleParameters("divide(1,2)");
        assertEquals("1/2", divide.execute());
        divide.handleParameters("divide(x,x)");
        assertEquals("1", divide.execute());
        divide.handleParameters("divide(x,2x)");
        assertEquals("1/2", divide.execute());
        divide.handleParameters("divide(x^2,x)");
        assertEquals("x", divide.execute());
    }
    @Test
    public void osamaaria(){
        divide.handleParameters("divide(1/2x^5-x^2+5x-2/3,x^2+2x+1)");
        assertEquals("1/2x^3-x^2+3/2x-3 and the remainder is 19/2x+7/3", divide.execute());
        divide.handleParameters("divide(5x^3-3x^2+2x+1,-4x+2)");
        assertEquals("-5/4x^2+1/8x-7/16 and the remainder is 15/8", divide.execute());
        divide.handleParameters("divide(x^2+2x+1,x+1)");
        assertEquals("x+1", divide.execute());
    }
}