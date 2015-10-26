/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.Map;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeroen
 */
public class PolynomialTest {
    
    public PolynomialTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parsePoly method, of class Polynomial.
     */
    @Test
    public void testParsePoly() {
        System.out.println("parsePoly");
        Polynomial instance = null;
        String expResult = "";
        String result = instance.parsePoly();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTerms method, of class Polynomial.
     */
    @Test
    public void testGetTerms() {
        System.out.println("getTerms");
        Polynomial instance = null;
        Map<Integer, Integer> expResult = null;
        Map<Integer, Integer> result = instance.getTerms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTerms method, of class Polynomial.
     */
    @Test
    public void testSetTerms() {
        System.out.println("setTerms");
        Map<Integer, Integer> a_terms = null;
        Polynomial instance = null;
        instance.setTerms(a_terms);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeModulo method, of class Polynomial.
     */
    @Test
    public void testChangeModulo() {
        System.out.println("changeModulo");
        int n = 0;
        Polynomial instance = null;
        instance.changeModulo(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Polynomial.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Polynomial p = null;
        Polynomial instance = null;
        instance.add(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class Polynomial.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        Polynomial p = null;
        Polynomial instance = null;
        instance.divide(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gcd method, of class Polynomial.
     */
    @Test
    public void testGcd() {
        System.out.println("gcd");
        Polynomial p = null;
        Polynomial instance = null;
        instance.gcd(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modulo method, of class Polynomial.
     */
    @Test
    public void testModulo() {
        System.out.println("modulo");
        Polynomial p = null;
        Polynomial instance = null;
        instance.modulo(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class Polynomial.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        Polynomial p = null;
        Polynomial instance = null;
        instance.multiply(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scalar method, of class Polynomial.
     */
    @Test
    public void scalarCheck() {
        System.out.println("scalarCheck()");
        /* 2X^1+1X^1+1X^0 times 2*/
        Map<Integer,Integer> test1 = new HashMap();
        Polynomial p = new Polynomial(1);
        
        test1.put(2,1);
        test1.put(1,1);
        test1.put(1,0);
        
        p.setTerms(test1);
        int scalar = 2;
        
        /*  */

    }

    /**
     * Test of subtract method, of class Polynomial.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Polynomial p = null;
        Polynomial instance = null;
        instance.subtract(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of xGCD method, of class Polynomial.
     */
    @Test
    public void testXGCD() {
        System.out.println("xGCD");
        Polynomial p = null;
        Polynomial instance = null;
        instance.xGCD(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
