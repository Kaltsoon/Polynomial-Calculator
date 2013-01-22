package utilsTests;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class StringToPolynomialTest {
    StringModifier mod;
    public StringToPolynomialTest() {
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
    public void erikoisiaPolynomeja(){
        Polynomial pol = new Polynomial();
        pol.addTerm(new Term(1,1,1));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("x").toString());
        pol = new Polynomial();
        pol.addTerm(new Term(-1,1,1));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("-x").toString());
        pol = new Polynomial();
        pol.addTerm(new Term(1,1,0));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("1").toString());
        pol = new Polynomial();
        pol.addTerm(new Term(0,1,0));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("0").toString());
        pol = new Polynomial();
        pol.addTerm(new Term(1,2,0));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("1/2").toString());
        pol = new Polynomial();
        pol.addTerm(new Term(-2,1,0));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("-2").toString());
    }
    public void tavallisiaPolynomeja(){
        Polynomial pol = new Polynomial();
        pol.addTerm(new Term(1,1,1));
        pol.addTerm(new Term(2,1,2));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("2x^2+x").toString());
        pol = new Polynomial();
        pol.addTerm(new Term(2,1,1));
        pol.addTerm(new Term(1,1,2));
        pol.addTerm(new Term(1,1,0));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("x^2+2x+1").toString());
        pol = new Polynomial();
        pol.addTerm(new Term(2,1,1));
        pol.addTerm(new Term(1,2,2));
        pol.addTerm(new Term(-1,1,0));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("1/2x^2+2x-1").toString());
        pol = new Polynomial();
        pol.addTerm(new Term(-2/3,1,1));
        pol.addTerm(new Term(3,1,2));
        pol.addTerm(new Term(-1,5,0));
        assertEquals(pol.toString(),mod.turnIntoPolynomial("3x^2-2/3x-1/5").toString());
    }
}
