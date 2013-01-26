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
import commands.ValueAt;
/**
 *
 * @author Kalle
 */
public class ValueAtTest {
    private ValueAt value;
    public ValueAtTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.value=new ValueAt();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void erikoistapauksia(){
       value.handleParameters("valueAt(x,1)");
       assertEquals("1.0", value.execute());
       value.handleParameters("valueAt(x^2,2)");
       assertEquals("4.0", value.execute());
       value.handleParameters("valueAt(x,-1)");
       assertEquals("-1.0", value.execute());
       value.handleParameters("valueAt(x^2,-1)");
       assertEquals("1.0", value.execute());
    }
    @Test
    public void arvoja(){
       value.handleParameters("valueAt(x^2+2x+1,1)");
       assertEquals("4.0", value.execute());
       value.handleParameters("valueAt(-4x^2+5x+10,9)");
       assertEquals("-269.0", value.execute());
       value.handleParameters("valueAt(4/5x^3+2x^2-4x+1/3,1/2)");
       assertEquals("-1.0666666666666667", value.execute());
    }
}
