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
import commands.Sum;
/**
 *
 * @author Kalle
 */
public class SumTest {
    private Sum sum;
    public SumTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.sum=new Sum();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void erikoistapaukset(){
       sum.handleParameters("sum(0,0,0,0)");
       assertEquals("0", sum.execute());
       sum.handleParameters("sum(1,0)");
       assertEquals("1", sum.execute());
       sum.handleParameters("sum(0,x)");
       assertEquals("x", sum.execute());
       sum.handleParameters("sum(x,x,x)");
       assertEquals("3x", sum.execute());
       sum.handleParameters("sum(1,x)");
       assertEquals("x+1", sum.execute());
       sum.handleParameters("sum(-x,x)");
       assertEquals("0", sum.execute());
       sum.handleParameters("sum(2x,-x)");
       assertEquals("x", sum.execute());
       sum.handleParameters("sum(x,-1)");
       assertEquals("x-1", sum.execute());
    }
    @Test
    public void summia(){
       sum.handleParameters("sum(2x+1,x^2+3)");
       assertEquals("x^2+2x+4", sum.execute());
       sum.handleParameters("sum(-4x^2+2x-5,x^2+x,x+5,4)");
       assertEquals("-3x^2+4x+4", sum.execute());
       sum.handleParameters("sum(1/2x^2+2x-5,2/3x^2+2/5x+1/4)");
       assertEquals("7/6x^2+12/5x-19/4", sum.execute());
    }
}
