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
import utils.*;
/**
 *
 * @author Kalle
 */
public class FractionTest {
    private Fraction frac;
    private StringModifier mod;
    public FractionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        frac=new Fraction();
        mod=new StringModifier();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void murtolukujenSumma(){
        assertEquals("5/4",mod.bestForm(frac.sum(1, 2, 3, 4)));
        assertEquals("1",mod.bestForm(frac.sum(1, 2, 1, 2)));
        assertEquals("-1",mod.bestForm(frac.sum(-1, 2, -1, 2)));
        assertEquals("-3/40",mod.bestForm(frac.sum(5, 8, -14, 20)));
        assertEquals("22/15",mod.bestForm(frac.sum(2, 3, 4, 5)));
    }
    @Test
    public void murtolukujenTulo(){
        assertEquals("3/8",mod.bestForm(frac.multiplication(1, 2, 3, 4)));
        assertEquals("1/4",mod.bestForm(frac.multiplication(1, 2, 1, 2)));
        assertEquals("1",mod.bestForm(frac.multiplication(-1, 1, -1, 1)));
        assertEquals("-7/16",mod.bestForm(frac.multiplication(5, 8, -14, 20)));
        assertEquals("8/15",mod.bestForm(frac.multiplication(2, 3, 4, 5)));     
    }
    @Test
    public void murtolukujenOsamaara(){
        assertEquals("2/3",mod.bestForm(frac.division(1, 2, 3, 4)));
        assertEquals("1",mod.bestForm(frac.division(1, 2, 1, 2)));
        assertEquals("-1",mod.bestForm(frac.division(1, 1, -1, 1)));
        assertEquals("-25/28",mod.bestForm(frac.division(5, 8, -14, 20)));
        assertEquals("5/6",mod.bestForm(frac.division(2, 3, 4, 5))); 
    }
}
