/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rene
 */
public class FiniteFieldTest {
    
    public FiniteFieldTest() {
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
     * Test of sum method, of class FiniteField.
     */
    @Test
    public void testSum() {
        System.out.println("sum");
        Polynomial p1 = null;
        Polynomial p2 = null;
        FiniteField instance = null;
        Polynomial expResult = null;
        Polynomial result = instance.sum(p1, p2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    @Test
    public void field() throws Exception{
        System.out.println("field");
        Parser instance = new Parser();
        String test = "1X^1+1X^0";
        Polynomial poly = instance.stringToPoly(test, 2);
        FiniteField field = new FiniteField(poly);
        System.out.println(field);
    }
}
