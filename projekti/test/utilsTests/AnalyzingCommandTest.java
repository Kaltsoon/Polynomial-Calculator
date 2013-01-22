/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilsTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.StringModifier;
import domain.*;
/**
 *
 * @author Kalle
 */
public class AnalyzingCommandTest {
    private StringModifier mod;
    public AnalyzingCommandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mod=new StringModifier();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void parametritLuetaanOikein(){
        assertEquals("x^2+2x+1",mod.readParameters("komento(x^2+2x+1,x^2-5,-x+4)")[0]);
        assertEquals("x^2-5",mod.readParameters("komento(x^2+2x+1,x^2-5,-x+4)")[1]);
        assertEquals("-x+4",mod.readParameters("komento(x^2+2x+1,x^2-5,-x+4)")[2]);
    }
    @Test
    public void komentoLuetaanOikein(){
        assertEquals("komento1",mod.readCommand("komento1(plaaplaa,plaa,plaaa)"));
        assertEquals("komento2",mod.readCommand("komento2(plaaplaa,plaa,plaaa)"));
        assertEquals("3komento",mod.readCommand("3komento(plaaplaa,plaa,plaaa)"));
    }
}
