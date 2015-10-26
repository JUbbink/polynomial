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
    
    public void main(String args[]){
        Scanner s = new Scanner(System.in);
        Parser(s);
    }
    
    public void Parser(Scanner s){
        String l ="";
        
        while(s.hasNext() == true){   //parsing input
            l = s.nextLine();
        }
        String[] parts = l.split(" ");//this gets me an array of all the separate elements
        //List<String> items = Arrays.asList(str.split("\\s*,\\s*"));

        
        if(parts.length == 5){//either case 4 or 5
            int prime = Integer.parseInt(parts[3]);
            Polynomial poly1 = stringToPoly(parts[1],prime);
            Polynomial poly2 = stringToPoly(parts[2],prime);
            
            if(parts[4].equals("div")){//case 5
                //do division
                poly1.divide(poly2);
                System.out.println(poly1.parsePoly());
            }
            else if(parts[4].equals("gcd")){//case 4
                //do gcd
                poly1.gcd(poly2);
                System.out.println(poly1.parsePoly());
                poly1.xGCD(poly2);
                System.out.println(poly1.parsePoly());
            }
        }
        else if(parts.length==4){//remaining three cases
            
            if(verifyPart(parts[1]) && verifyPart(parts[2]) && verifyPart(parts[3])){//contains a polynomial, thus case1
                Polynomial poly1 = stringToPoly(parts[1],1);
                Polynomial poly2 = stringToPoly(parts[2],1);
                Polynomial poly3 = stringToPoly(parts[3],1);
                
                //do math
            }
                
            else if(verifyPart(parts[1]) && !verifyPart(parts[2]) && !verifyPart(parts[3])){//is a coefficient, thus case 3
                int coefficient = Integer.parseInt(parts[2]);
                int prime = Integer.parseInt(parts[3]);
                Polynomial poly1 = new Polynomial(prime);
                
                poly1.scalar(coefficient);
                System.out.println(poly1.parsePoly());
            }
                
            else if(verifyPart(parts[1]) && verifyPart(parts[2]) && !verifyPart(parts[3])){//only case 2 remains
                int prime = Integer.parseInt(parts[3]);
                Polynomial poly1 = stringToPoly(parts[1],prime);
                Polynomial poly2 = stringToPoly(parts[2],prime);
                
                
                poly1.add(poly2);
                System.out.println(poly1.parsePoly());
                
                poly1 = stringToPoly(parts[1],prime);
                poly2 = stringToPoly(parts[2],prime);
                poly1.subtract(poly2);
                System.out.println(poly1.parsePoly());
                
                poly1 = stringToPoly(parts[1],prime);
                poly2 = stringToPoly(parts[2],prime);
                poly1.multiply(poly2);
                System.out.println(poly1.parsePoly());
            }
        }
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
    
    private boolean verifyPart(String s) {
        Scanner stringScanner = new Scanner(s);

        /* Check if there is a coefficient */
        if(!(stringScanner.hasNextInt())) {
            return false;
        } else {
            /* Consume int */
            stringScanner.nextInt();
        }

        /* Check for the "X^" */
        if(!(stringScanner.hasNext("X^"))) {
            return false;
        } else {
            /* Consume "X^" */
            stringScanner.next("X^");
        }

        /* Check if there is an order */
        if(!(stringScanner.hasNextInt())) {
            return false;
        } else {
            /* Consume int */
            stringScanner.nextInt();
        }

        return true;
    }
    public Polynomial stringToPoly(String s, int m) {

        /* Split up and process the terms */
        String parts[] = s.split("+");
        Map<Integer, Integer> newPolyTerms = new HashMap();

        for (String p:parts) {
            /* Check if p's formatting is valid */
            if (verifyPart(p)) {
                Scanner partScanner = new Scanner(p);

                int newCoefficient = partScanner.nextInt();
                partScanner.next("X^");
                int newOrder = partScanner.nextInt();

                newPolyTerms.put(newCoefficient, newOrder);
            } else {
                /* Do error handling */
            }
        }

        /* Create new polynomial and set it's terms to the new terms */
        Polynomial newPoly = new Polynomial(m); /* TODO add prime */
        newPoly.setTerms(newPolyTerms);
        return newPoly;
    }
        
        // three polynomials, where you decide if poly1 = poly2 mod poly3
        // case1 is: "poly" poly1 poly2 poly3

        // two polynomials mod prime, where you do sum, difference, product
        // case2 is: "poly" poly1 poly2 prime
        
        // a multiplication coefficient , and one polynomial mod prime where you do the scalar multiplication
        // case3 is: "poly" poly1 coefficient prime
 
        // two polynomials, mod prime, do xGCD
        // case4 is: "poly" poly1 poly2 prime "gcd"
        
        // two polynomials, mod prime, do long division (return quotient and remainder)
        // case5 is: "poly" poly1 poly2 prime "div"
        
        //prime number p and irreducible polynomial, return addition and multiplication table
        //case1 is: "FF" prime poly1
        
        // two field elements a,b, field, return sum a+b, product ab, quotient ab^-1 if b!=0 
        //case2 is: "FF" a b field
        
        //primitivity of field element
        //case3 is: "FF" a field
        
        //find primitive elements in a field
        //case4 is: "FF" field
        
        //test irreducibility of a poly mod p, return irreducible polynomials
        //case5 is: "FF" poly modp   
}