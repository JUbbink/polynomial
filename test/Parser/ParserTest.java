/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;


import org.junit.Test;
/**
 *
 * @author Jeroen
 */
public class ParserTest {
    
    /**
     * Test of stringtoPoly method, of class Parser.
     */
    @Test
    public void testStringtoPoly() throws Exception {
        System.out.println("stringToPoly test: ");
        Parser instance = new Parser();
        String test = "-5X^3+4x^-2-1X^7";
        Polynomial poly = instance.stringToPoly(test);
        String result = poly.parsePoly();
        System.out.println("Output: "+result);
    }
    
}
