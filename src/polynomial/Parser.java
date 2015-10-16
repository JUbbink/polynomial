package polynomial;
import java.util.Scanner;

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
       
        //Possible inputs are: either start with FF or poly. If poly, there are a few options.
        //either three polynomials, where you decide if poly1 = poly2 mod poly3
        //two polynomials mod prime, where you do sum, difference, product
        //a multiplication coefficient , and one polynomial mod prime where you do the scalar multiplication
        //two polynomials, mod prime, do xGCD
        //two polynomials, mod prime, do long division (return quotient and remainder)
        
        while(s.hasNext()==true){
            String l = s.nextLine();
        }
        String[] parts = l.split(" ");//this gets me an array of all the separate elements
        
        
        if(parts[0] == "poly"){
            String poly1 = parts[1];
            String poly2 = parts[2];
            String mod = parts[3];
            
            if(mod.matches(*mod*)){
                    //then we have two polynomials and a coefficient do division, multiplication, addition
            }
            else if(mod.substring(0, 1).matches("[0-9]")){
                //then decide if the first two polynomials are equal modulo the third polynomial.
            }
        }
        else if(parts[0]== "FF"){
            //TODO - Finite Field parsing
        }
        
        
        //constraints on input are such that there is an initial selector, such
        //as division, addition, multiplication or subtraction.
    }
    
    
}
