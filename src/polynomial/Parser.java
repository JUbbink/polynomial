package polynomial;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.*;

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
    
    public static void main(String args[]){
        
    }
    
    public void Parser(Scanner s){
        String l ="";
        
        while(s.hasNext()==true){   //parsing input
            l = s.nextLine();
        }
        String[] parts = l.split(" ");//this gets me an array of all the separate elements
        //List<String> items = Arrays.asList(str.split("\\s*,\\s*"));

        
        if(parts.length == 5){//either case4 or 5
            Polynomial poly1 = new Polynomial();
            Polynomial poly2 = new Polynomial();
            int prime = Integer.parseInt(parts[3]);
                
            int count = parts[1].replaceAll("\\D", "").length();
            for(int i = 0; i < count; i++){
                poly1.setCoefficient(Integer.parseInt(parts[1].charAt(i)+""));
                poly1.setOrder(count);
            }
            poly1.create();
                        
            count = parts[2].replaceAll("\\D", "").length();
            for(int i = 0; i < count; i++){
                poly2.setCoefficient(Integer.parseInt(parts[2].charAt(i)+""));
                poly2.setOrder(count);
            }
            poly2.create();
                
            if(parts[4].equals("div")){//case 5
                //do division
            }
            else if(parts[4].equals("gcd")){//case 4
                //do gcd
            }
        }
        else if(parts.length==4){//remaining three cases
            Polynomial poly1 = new Polynomial();
            int count = parts[1].replaceAll("\\D", "").length();
            for(int i = 0; i < count; i++){
                poly1.setCoefficient(Integer.parseInt(parts[1].charAt(i)+""));
                poly1.setOrder(count);
            }
            poly1.create();
            
            if(parts[1].contains(",") && parts[2].contains(",") && parts[3].contains(",")){//contains a polynomial, thus case1
                Polynomial poly2 = new Polynomial();
                Polynomial poly3 = new Polynomial();

                count = parts[2].replaceAll("\\D", "").length();
                for(int i = 0; i < count; i++){
                    poly2.setCoefficient(Integer.parseInt(parts[2].charAt(i)+""));
                    poly2.setOrder(count);
                }
                poly2.create();
                
                count = parts[3].replaceAll("\\D", "").length();
                for(int i = 0; i < count; i++){
                    poly3.setCoefficient(Integer.parseInt(parts[2].charAt(i)+""));
                    poly3.setOrder(count);
                }
                poly3.create();
                //do math
            }
                
            else if(parts[1].contains(",") && !parts[2].contains(",") && !parts[3].contains(",")){//is a coefficient, thus case 3
                int coefficient = Integer.parseInt(parts[2]);
                int prime = Integer.parseInt(parts[3]);
                
                //do math
            }
                
            else if(parts[1].contains(",") && parts[2].contains(",") && !parts[3].contains(",")){//only case 2 remains
                Polynomial poly2 = new Polynomial();
                int prime = Integer.parseInt(parts[3]);
                
                count = parts[2].replaceAll("\\D", "").length();
                for(int i = 0; i < count; i++){
                  poly2.setCoefficient(Integer.parseInt((parts[2].charAt(i))+""));
                    poly2.setOrder(count);
                }
                poly2.create();
                //do math
            }
        }
    }
    
    private String parsePoly(Polynomial p){
        String output = "";
        for(int i = 0; i < p.getSize(); i++){
            output = output +"+"+ p.getCoefficient(i)+"X^"+p.getOrder(i);
        }
        return output;
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
