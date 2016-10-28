/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import static org.junit.Assert.assertEquals;
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
    public void testStringtoPoly1() throws Exception {
        System.out.println("stringToPoly test 1: ");
        Parser instance = new Parser();
        String test = "-5X^3+4x^-2-1X^7";
        Polynomial poly = instance.stringToPoly(test, 20);
        String result = poly.toString();
        String output = "4X^-2 + 15X^3 + 19X^7";
        assertEquals(output, result);
    }

    /**
     * Test of stringtoPoly method, of class Parser.
     */
    @Test
    public void testStringtoPolyMinimal() throws Exception {
        System.out.println("stringToPoly test 2: ");
        Parser instance = new Parser();
        String test = "1x^1";
        Polynomial poly = instance.stringToPoly(test, 20);
        String result = poly.toString();
        String output = "1X^1";
        assertEquals(output, result);
    }

    /**
     * Test of stringtoPoly method, of class Parser.
     */
    @Test
    public void testStringtoPolyLarge() throws Exception {
        System.out.println("stringToPoly test 2: ");
        Parser instance = new Parser();
        String test = "2345x^16-315131x^2+435134131x^5";
        Polynomial poly = instance.stringToPoly(test, 17);
        String result = poly.toString();
        String output = "15X^2 + 6X^5 + 16X^16";
        assertEquals(output, result);
    }

    @Test
    public void testStringtoPoly2() throws Exception {
        System.out.println("stringToPoly test 2: ");
        Parser instance = new Parser();
        String test = "-15x^16+12x^-2+3x^5";
        Polynomial poly = instance.stringToPoly(test, 20);
        String result = poly.toString();
        String output = "12X^-2 + 3X^5 + 5X^16";
        assertEquals(output, result);
    }

    @Test
    public void testStringtoPolyNegative() throws Exception {
        System.out.println("stringToPoly test 2: ");
        Parser instance = new Parser();
        String test = "-15x^-16-12x^-2-3x^-5";
        Polynomial poly = instance.stringToPoly(test, 20);
        String result = poly.toString();
        String output = "5X^-16 + 17X^-5 + 8X^-2";
        assertEquals(output, result);
    }

    @Test
    public void testStringtoPolyZero() throws Exception {
        System.out.println("stringToPoly test 2: ");
        Parser instance = new Parser();
        String test = "20x^-16-12x^-2-3x^-5";
        Polynomial poly = instance.stringToPoly(test, 20);
        String result = poly.toString();
        String output = "0X^-16 + 17X^-5 + 8X^-2";
        assertEquals(output, result);
    }
    
    @Test
    public void testStringtoPolyZeroPower() throws Exception {
        System.out.println("stringToPoly test 2: ");
        Parser instance = new Parser();
        String test = "21x^0-12x^-2-3x^-5";
        Polynomial poly = instance.stringToPoly(test, 20);
        String result = poly.toString();
        String output = "17X^-5 + 8X^-2 + 1X^0";
        assertEquals(output, result);
    }
    
    @Test
    public void testStringtoPoly3() throws Exception {
        System.out.println("stringToPoly test 2: ");
        Parser instance = new Parser();
        String test = "21x^16-12x^-2-3x^-5";
        Polynomial poly = instance.stringToPoly(test, 1);
        String result = poly.toString();
        String output = "0X^-5 + 0X^-2 + 0X^16";
        assertEquals(output, result);
    }

}
