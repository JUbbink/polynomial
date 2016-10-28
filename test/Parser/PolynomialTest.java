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
import org.junit.Ignore;
import static org.junit.Assert.*;


/**
 *
 * @author Jeroen
 */
public class PolynomialTest {
    
    public PolynomialTest() {
    }
    
    @Ignore
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Ignore
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Ignore
    @Before
    public void setUp() {
    }
    
    @Ignore
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Polynomial.
     */
    @Ignore
    @Test
    public void testParsePoly() {
        System.out.println("parsePoly");
        Polynomial instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTerms method, of class Polynomial.
     */
    @Ignore
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
    @Ignore
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
     * Test of add method, of class Polynomial.
     */
    @Ignore
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
    @Ignore
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
    @Ignore
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
     * Test of multiply method, of class Polynomial.
     */
    @Test
    public void testMultiply() {
        System.out.println("Multiplication Test");
        Map<Integer,Integer> map = new HashMap();
        map.put(2,1);
        map.put(3,1);

        
        Map<Integer,Integer> map2 = new HashMap();
        map2.put(4,1);
        map2.put(3,1);

        
        Polynomial p1 = new Polynomial(map);
        Polynomial p2 = new Polynomial(map2);
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        Polynomial p3 = p1.multiply(p2);
        System.out.println(p3.toString());
        
        System.out.println("Inverse Test: "+IntegerModP.divide(4,2,2));
 
    }

    @Ignore
    @Test
    public void testUpdateModulo(){
        System.out.println("Testing update/change Modulo");
        int m1 = 2;
        int m2 = 4;
        
        Map<Integer,Integer> map = new HashMap();
        map.put(0,3);
        map.put(1,3);
        map.put(2,3);
        map.put(3,3);
        map.put(4,3);
        
        System.out.println(map);
        
        Map<Integer,Integer> map2 = new HashMap();
        map2.put(1,3);
        map2.put(2,3);
        map2.put(3,3);
        map2.put(4,3);
        
        Polynomial p1 = new Polynomial(map,m1);
        Polynomial p2 = new Polynomial(map,m2);
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        
    }
    
    
    /**
     * Test of scalar method, of class Polynomial.
     */
    @Ignore
    @Test
    public void scalarCheck() {
        System.out.println("scalarCheck()");
        /* 2X^1+1X^1+1X^0 times 2*/
        Map<Integer,Integer> test1 = new HashMap();
        Map<Integer,Integer> test2 = new HashMap();

        test1.put(2,1);
        test1.put(1,1);
        test1.put(0,1);
        
        test2.put(2,2);
        test2.put(1,2);
        test2.put(0,2);
        
        Polynomial p1 = new Polynomial(test1,20);
        Polynomial p2 = new Polynomial(test2,20);
        
        int s = 2;
        
        Polynomial p3 = new Polynomial();
        p3 = p1.scalar(s);
        
        System.out.println("p1= " + p1.toString());
        System.out.println("p2= " + p2.toString());
        System.out.println("p1*"+s+"= "+p3.toString());

        /*  */
    }

    /**
     * Test of subtract method, of class Polynomial.
     */

    @Test
    public void testSubtract() {
        System.out.println("subtract");
        
        Map<Integer,Integer> map = new HashMap();
        map.put(0,3);
        map.put(1,3);
        map.put(2,3);
        map.put(3,3);
        map.put(4,3);
        map.put(8,3);
        
        System.out.println(map);
        
        Map<Integer,Integer> map2 = new HashMap();
        map2.put(1,3);
        map2.put(2,3);
        map2.put(6,3);
        map2.put(4,3);
        
        Polynomial p1 = new Polynomial(map,2);
        Polynomial p2 = new Polynomial(map2,5);
        System.out.println(p1.toString());
        System.out.println(p2.toString()); 
        Polynomial p3 = p1.add(p2);
        System.out.println(p3.toString());
    }

    /**
     * Test of xGCD method, of class Polynomial.
     */
    @Ignore
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
