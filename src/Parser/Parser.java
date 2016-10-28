package Parser;

import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 @author Jeroen
 //TODO - define input parameters
 
 Types of inputs: 
 Main:
 aX^b+c^X^(b-1) etc
 two polynomials with integer coefficients
 integer coefficients mod p
 
 input two polynomials + division, return two polynomials and d symbol.
 
 input three polynomials = check if the first two are equal mod the third.
 
 Finite Field:
 input: prime p, exponent n, return field with p^n elements, for some
 irreducible polynomial.
 
 p, and irreducible Main, produce addition and multiplication table.
 
 two field elements a and b, produce the sum, product, quotient ab^-1 (if b
 != 0) in a field.
 
 single field element - 
 */
public class Parser {

    public static void main(String args[]) {
        Parser p = new Parser();
        p.Parser();
    }

    public void Parser() {
        String s = "";
        System.out.println("Please enter input:");
        Scanner scan = new Scanner(System.in);
        while (true) {
            s = scan.nextLine();
            String[] parts = s.split(" ");//this gets me an array of all the separate elements

            // three polynomials, where you decide if poly1 = poly2 mod poly3
            // case1 is: "poly" "polymod" poly1 poly2 poly3
            // two polynomials mod prime, where you do sum, difference, product
            // case2 is: "poly" "basic" poly1 poly2 prime
            // a multiplication coefficient , and one polynomial mod prime where you do the scalar multiplication
            // case3 is: "poly" "scalar" poly1 coefficient prime
            // two polynomials, mod prime, do xGCD
            // case4 is: "poly" "GCD" poly1 poly2 prime
            // two polynomials, mod prime, do long division (return quotient and remainder)
            // case5 is: "poly" "division" poly1 poly2 prime
            if (parts.length > 0) {
                if (parts[0].equals("poly")) {
                    if (parts[1].equals("polymod")) {
                        try {
                            Polynomial poly1 = stringToPoly(parts[2], Integer.MAX_VALUE);
                            Polynomial poly2 = stringToPoly(parts[3], Integer.MAX_VALUE);
                            Polynomial poly3 = stringToPoly(parts[4], Integer.MAX_VALUE);

                            Polynomial[] poly4 = poly1.divide(poly3);
                            Polynomial[] poly5 = poly2.divide(poly3);

                            //do math
                            System.out.println("Remainder of first Division: " + poly4[1].toString());
                            System.out.println("Remainder of Second Divison: " + poly5[1].toString());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (parts[1].equals("basic")) {
                        try {
                            int prime = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2], prime);
                            Polynomial poly2 = stringToPoly(parts[3], prime);

                            Polynomial sum = poly1.add(poly2);
                            System.out.println("Sum: " + sum.toString());

                            Polynomial difference = poly1.subtract(poly2);
                            System.out.println("Difference: " + difference.toString());

                            Polynomial product = poly1.multiply(poly2);
                            System.out.println("Product: " + product.toString());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (parts[1].equals("scalar")) {
                        try {
                            int prime = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2], prime);
                            int scalar = Integer.parseInt(parts[3]);

                            Polynomial poly2 = poly1.scalar(scalar);
                            System.out.println("Scalar: " + poly2.toString());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (parts[1].equals("GCD") || parts[1].equals("gcd")) {
                        try {
                            int prime = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2], prime);
                            Polynomial poly2 = stringToPoly(parts[3], prime);

                            Polynomial gcdOutput = poly1.gcd(poly2);
                            Polynomial[] xGCDOutput = poly1.xGCD(poly2);
                            System.out.println("GCD: " + gcdOutput.toString());
                            System.out.println("xGCD: x=" + xGCDOutput[0].toString() + " y= " + xGCDOutput[1].toString());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (parts[1].equals("division")) {
                        try {
                            int prime = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2], prime);
                            Polynomial poly2 = stringToPoly(parts[3], prime);

                            Polynomial[] outputArray = poly1.divide(poly2);
                            System.out.println("Division: q = " + outputArray[0].toString());
                            System.out.println("r = " + outputArray[1].toString());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Please make a valid selection.");
                    }
                } else if (parts[0].equals("FF")) {
                    //prime number p and irreducible polynomial, return addition and multiplication table
                    //case1 is: "FF" "table" "prime" "poly"
                    if (parts[1].equals("table")) {
                        try {
                            int m = Integer.parseInt(parts[2]);
                            Polynomial poly = stringToPoly(parts[3], m);
                            FiniteField f = new FiniteField(poly);
                            System.out.println("Addition Table:");
                            System.out.println(f.additionTable());
                            System.out.println("Addition Table:");
                            System.out.println(f.multiplicationTable());
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    // two field elements a,b, field, return sum a+b, product ab, quotient ab^-1 if b!=0 
                    //case2 is: "FF" "sum" "poly a" "poly b" "prime" "generating poly"
                    if (parts[1].equals("sum")) {
                        try {
                            int m = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2], m);
                            Polynomial poly2 = stringToPoly(parts[3], m);
                            Polynomial generator = stringToPoly(parts[5], m);

                            FiniteField f = new FiniteField(generator);
                            Polynomial sum = f.sum(poly1, poly2);
                            Polynomial product = f.product(poly1, poly2);
                            Polynomial q = f.quotient(poly1, poly2);

                            System.out.println("Sum is " + sum.toString());
                            System.out.println("Product is " + product.toString());
                            if (!(q.getMod() == Integer.MAX_VALUE)) {
                                System.out.println("Inverse does not exist");
                            } else {
                                System.out.println("Quotient is " + q.toString());
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    //primitivity of field element
                    //case3 is: "FF" "primitivity" "poly a" "prime" "generating poly"
                    if (parts[1].equals("primitivity")) {
                        try {
                            int m = Integer.parseInt(parts[3]);
                            Polynomial poly1 = stringToPoly(parts[2], m);
                            Polynomial generator = stringToPoly(parts[4], m);
                            FiniteField f = new FiniteField(generator);
                            if (f.isPrimitive(poly1)) {
                                System.out.println("This Polynomial is a "
                                        + "primitive element of this "
                                        + "finite field.");
                            } else {
                                System.out.println("This Polynomial is NOT a "
                                        + "primitive element of this "
                                        + "finite field.");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    //find primitive elements in a field
                    //case4 is: "FF" "find" "prime" "generating poly"
                    if (parts[1].equals("find")) {
                        try {
                            int m = Integer.parseInt(parts[2]);
                            Polynomial poly = stringToPoly(parts[3], m);
                            FiniteField f = new FiniteField(poly);
                            Polynomial poly1 = f.primitive();
                            System.out.println(poly.toString());
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    //test irreducibility of a poly mod p, return irreducible polynomials
                    //case5 is: "FF" irr poly prime
                    //NYI
                    if (parts[1].equals("irr")) {
                        System.out.println("This has not yet been implemented.");
                    }
                } else {
                    System.out.println("Please make a valid selection.");
                }
            } else {
                System.out.println("Please enter a valid command.");
            }
        }
    }

    private int getNumberOfDigits(String s) {
        int j = 0;
        while (j < s.length() && Character.isDigit(s.charAt(j))) {
            j++;
        }
        return j;
    }

    public Polynomial stringToPoly(String input, int p) throws Exception {
        Map<Integer, Integer> map = new HashMap();

        if (Character.isDigit(input.charAt(0))) {
            input = "+" + input;
        }
        while (!input.equals("")) {
            int sign = 1;
            if (input.substring(0, 1).equals("-")) {
                sign = -1;
            }
            input = input.substring(1);
            int numberOfDigits = getNumberOfDigits(input);
            int coef = sign * Integer.parseInt(input.substring(0, numberOfDigits));
            input = input.substring(numberOfDigits);
            if (!(input.substring(0, 2).equals("X^")) && !(input.substring(0, 2).equals("x^"))) {
                throw new Exception("No X^ found.");
            }
            input = input.substring(2);
            if (input.substring(0, 1).equals("-")) {
                input = input.substring(1);
                sign = -1;
            } else {
                sign = 1;
            }
            numberOfDigits = getNumberOfDigits(input);
            int exp = sign * Integer.parseInt(input.substring(0, numberOfDigits));
            input = input.substring(numberOfDigits);
            map.put(exp, coef);
        }
        return new Polynomial(map, p);
    }

}
