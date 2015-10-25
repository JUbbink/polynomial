package Parser;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.*;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Jeroen
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
 * 
 */
public class Parser {
    
    public void main(String args[]){
        Scanner s = new Scanner(System.in);
        Parser(s);
    }
    
    public void Parser(Scanner s){
        String l ="";
        
        while(s.hasNext()==true){   //parsing input
            l = s.nextLine();
        }
        String[] parts = l.split(" ");//this gets me an array of all the separate elements
        //List<String> items = Arrays.asList(str.split("\\s*,\\s*"));

        
        if(parts.length == 5){//either case4 or 5
            int prime = Integer.parseInt(parts[3]);
            Polynomial poly1 = new Polynomial(prime);
            Polynomial poly2 = new Polynomial(prime);
            
            /* TODO: Fill HashMaps and setTerms */
            
            
                
            if(parts[4].equals("div")){//case 5
                //do division
                poly1.divide(poly2);
                System.out.println(parsePoly(poly1));
            }
            else if(parts[4].equals("gcd")){//case 4
                //do gcd
                poly1.gcd(poly2);
                System.out.println(parsePoly(poly1));
                poly1.xGCD(poly2);
                System.out.println(parsePoly(poly1));
            }
        }
        else if(parts.length==4){//remaining three cases
            
            if(parts[1].contains(",") && parts[2].contains(",") && parts[3].contains(",")){//contains a polynomial, thus case1
                Polynomial poly1 = new Polynomial(1);
                Polynomial poly2 = new Polynomial(1);
                Polynomial poly3 = new Polynomial(1);

            /* TODO: Fill HashMaps and setTerms */
                
                //do math
            }
                
            else if(parts[1].contains(",") && !parts[2].contains(",") && !parts[3].contains(",")){//is a coefficient, thus case 3
                int coefficient = Integer.parseInt(parts[2]);
                int prime = Integer.parseInt(parts[3]);
                Polynomial poly1 = new Polynomial(prime);
                
                /* TODO: Fill HashMaps and setTerms */
                
                poly1.scalar(coefficient);
                System.out.println(parsePoly(poly1));
            }
                
            else if(parts[1].contains(",") && parts[2].contains(",") && !parts[3].contains(",")){//only case 2 remains
                int prime = Integer.parseInt(parts[3]);
                Polynomial poly1 = new Polynomial(prime);
                Polynomial poly2 = new Polynomial(prime);
                
                /* TODO: Fill HashMaps and setTerms */
                
                poly1.add(poly2);
                System.out.println(parsePoly(poly1));
                
                poly1.subtract(poly2);
                System.out.println(parsePoly(poly1));
                
                poly1.multiply(poly2);
                System.out.println(parsePoly(poly1));
            }
        }
    }
        
//        else if(parts[0].equals("FF")){
//            if(parts[1].matches("\\d")){//case 1,2,3,5
//                if(parts[3].matches("a field")){//case 2
//                    int a = Integer.parseInt(parts[1]);
//                    int b = Integer.parseInt(parts[2]);
//                    
//                    //FiniteField field = new FiniteField();
//                    
//                    //return the sum, product, quotient ab^-1 if b!=0
//                }
//                else if(parts[2].contains(",")){//case1
//                    int prime = Integer.parseInt(parts[1]);
//                    Polynomial poly = new Polynomial();
//                    
//                    int count = parts[2].replaceAll("\\D", "").length();
//                    for(int i = 0; i < count; i++){
//                        poly.setCoefficient(Integer.parseInt((parts[2].charAt(i))+""));
//                        poly.setOrder(count);
//                    }
//                    poly.create();
//                    
//                    //do case1
//                }
//            }
//            else if(parts[1].matches("a field")){//case4
//                //do case4
//            }
        
            
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
    
    private String parsePoly(Polynomial p){
        String output = "";
        Map<Integer, Integer> terms = p.getTerms();
        
        for (Map.Entry<Integer,Integer> entry: terms.entrySet()){
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();  
            output = output + key + "X^" + value + " + ";  
        } 
        System.out.println(output);
        return output;
    }
    
    private boolean verify_part(String s) {
        Scanner string_scanner = new Scanner(s);

        /* Check if there is a coefficient */
        if(!(string_scanner.hasNextInt())) {
            return false;
        } else {
            /* Consume int */
            string_scanner.nextInt();
        }

        /* Check for the "X^" */
        if(!(string_scanner.hasNext("X^"))) {
            return false;
        } else {
            /* Consume "X^" */
            string_scanner.next("X^");
        }

        /* Check if there is an order */
        if(!(string_scanner.hasNextInt())) {
            return false;
        } else {
            /* Consume int */
            string_scanner.nextInt();
        }

        return true;
    }
    public Polynomial string_to_poly(String s) {

        /* Split up and process the terms */
        String parts[] = s.split("+");
        Map<Integer, Integer> new_poly_terms = new HashMap();

        for (String p:parts) {
            /* Check if p's formatting is valid */
            if (verify_part(p)) {
                Scanner part_scanner = new Scanner(p);

                int new_coefficient = part_scanner.nextInt();
                part_scanner.next("X^");
                int new_order = part_scanner.nextInt();

                new_poly_terms.put(new_coefficient, new_order);
            } else {
                /* Do error handling */
            }
        }

        /* Create new polynomial and set it's terms to the new terms */
        Polynomial new_poly = new Polynomial(16); /* TODO add prime */
        new_poly.setTerms(new_poly_terms);
        return new_poly;
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
        
        
//        else if(parts[0]== "FF"){
//            //TODO - Finite Field parsing
//            if(parts.length == 2){
//                //case4
//            }
//            else if(parts[2].matches("*mod*")){
//                //case5
//            }
//            else if(parts[2].matches("*X*")){
//                //case1
//            }
//            else if(parts[2].substring(0, 1).matches("[0-9]") && parts[2].length()==1){
//                //case2
//            }
//            else if(parts[2]=="a field"){ //TODO defining a field field/polynomial
//                //case3
//            }
//            
//        }
        
 //   }
    
}
