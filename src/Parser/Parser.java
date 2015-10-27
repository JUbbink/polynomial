package Parser;
import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.Map;
import java.util.HashMap;

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
    
    public static void main(String args[]){
        Parser p = new Parser();
        p.Parser();
    }
    
    public void Parser(){
        String s = "";
        System.out.println("Please enter input:");
        Scanner scan = new Scanner(System.in);
        while(true){
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

            if(parts.length>0){
                if(parts[0].equals("poly")){
                    if(parts[1].equals("polymod")){
                        try{
                            Polynomial poly1 = stringToPoly(parts[2],Integer.MAX_VALUE);
                            Polynomial poly2 = stringToPoly(parts[3],Integer.MAX_VALUE);
                            Polynomial poly3 = stringToPoly(parts[4],Integer.MAX_VALUE);

                            //do math
                            System.out.println("Not yet implemented.");
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }

                    }
                    else if(parts[1].equals("basic")){
                        try{
                            int prime = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2],prime);
                            Polynomial poly2 = stringToPoly(parts[3],prime);

                            Polynomial sum = poly1.add(poly2);
                            System.out.println("Sum: "+sum.parsePoly());

                            Polynomial difference = poly1.subtract(poly2);
                            System.out.println("Difference: "+difference.parsePoly());

                            Polynomial product = poly1.multiply(poly2);
                            System.out.println("Product: "+product.parsePoly());
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    else if(parts[1].equals("scalar")){
                        try{
                            int prime = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2],prime);
                            int scalar = Integer.parseInt(parts[3]);

                            Polynomial poly2 = poly1.scalar(scalar);
                            System.out.println("Scalar: "+poly2.parsePoly());
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    else if(parts[1].equals("GCD")||parts[1].equals("gcd")){
                        try{
                            int prime = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2],prime);
                            Polynomial poly2 = stringToPoly(parts[3],prime);

                            Polynomial gcdOutput = poly1.gcd(poly2);
                            Polynomial[] xGCDOutput = poly1.xGCD(poly2);
                            System.out.println("GCD: "+ gcdOutput.parsePoly());
                            System.out.println("xGCD: x="+xGCDOutput[0].parsePoly()+" y= "+xGCDOutput[1].parsePoly());
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    else if(parts[1].equals("division")){
                        try{
                            int prime = Integer.parseInt(parts[4]);
                            Polynomial poly1 = stringToPoly(parts[2], prime);
                            Polynomial poly2 = stringToPoly(parts[3], prime);

                            Polynomial[] outputArray = poly1.divide(poly2);
                            System.out.println("Division: q = "+outputArray[0].parsePoly());
                            System.out.println("r = "+outputArray[1].parsePoly());
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    else{
                        System.out.println("Please make a valid selection.");
                    }
                }
                else if(parts[0].equals("FF")){
                    System.out.println("This functionality is not implemented yet.");
                }
                else{
                    System.out.println("Please make a valid selection.");
                }
            }
            else{
                System.out.println("Please enter a valid command.");
            }
        }
    }
    
    private int getNumberOfDigits(String s){
        int j = 0;
        while(j < s.length() && Character.isDigit(s.charAt(j))) 
            j++;
        return j;
    }
    
    public Polynomial stringToPoly(String input, int p) throws Exception{
        Map<Integer,Integer> map = new HashMap();
        
        if(Character.isDigit(input.charAt(0))){
            input = "+"+input;
        }
        while(!input.equals("")){
            int sign = 1;
            if(input.substring(0, 1).equals("-")){
                sign = -1;
            }
            input = input.substring(1);
            int numberOfDigits = getNumberOfDigits(input);
            int coef = sign*Integer.parseInt(input.substring(0,numberOfDigits));
            input = input.substring(numberOfDigits);
            if(!(input.substring(0,2).equals("X^")) && !(input.substring(0,2).equals("x^"))){
                throw new Exception("No X^ found.");
            }
            input = input.substring(2);
            if(input.substring(0, 1).equals("-")){
                input = input.substring(1);
                sign = -1;
            }
            else 
                sign = 1;
            numberOfDigits = getNumberOfDigits(input);
            int exp = sign*Integer.parseInt(input.substring(0,numberOfDigits));
            input = input.substring(numberOfDigits);
            map.put(exp, coef);
        }
        return new Polynomial(map, p);
    }
        
        //prime number p and irreducible polynomial, return addition and multiplication table
        //case1 is: "FF" prime poly
        
        // two field elements a,b, field, return sum a+b, product ab, quotient ab^-1 if b!=0 
        //case2 is: "FF" a b field
        
        //primitivity of field element
        //case3 is: "FF" a field
        
        //find primitive elements in a field
        //case4 is: "FF" field
        
        //test irreducibility of a poly mod p, return irreducible polynomials
        //case5 is: "FF" poly prime
//        }
//    }
}