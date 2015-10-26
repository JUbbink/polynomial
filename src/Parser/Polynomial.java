package Parser;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jeroen
 */
public class Polynomial {
/* poly:
 *     ax^n + bx^o + cx^p
 * 
 * unary operations:
 * MIN
 * 
 * binary operators:
 * ADD poly poly   // Addition
 * DIV poly poly   // Division
 * GCD poly poly   // Greatest common devisor
 * MOD poly int    // Modulo
 * MUL poly poly   // Multiplication
 * SCL poly int    // Scalar
 * SUB poly poly   // Subtract
 * XGD poly poly   // Extended greatest common devisor
 * 
 * OPERATION   ARG1    ARG2
 * ADD         poly1   poly1   // add poly2 to poly1
 * SUB         poly1   poly2   // subtract poly2 from poly1
 * MUL         poly1   poly2   // multiply poly1 by poly2
 */

    /* Modulus prime */
    private int modulus;

    /* A map of orders to coefficients orders to coefficients */
    private Map<Integer, Integer> terms = new HashMap<>(); 

    /* Constructor */
    public Polynomial(int m) {
        this.modulus = m;
    }
    
    public Polynomial(Map<Integer,Integer> a_terms , int m){
        this.terms = new HashMap(a_terms);
        this.modulus = m;
        this.updateModulo();
    }
    
    public Polynomial(Map<Integer,Integer> a_terms){
        this.terms = new HashMap(a_terms);
        this.modulus = 1;
        this.updateModulo();
    }
    
    public Polynomial(){
        Map<Integer,Integer> newMap = new HashMap();
        
        this.terms=newMap;
        this.modulus = 1;
    }

    /* Re-modulates all terms, to be called after every change */
    public void updateModulo() {
        /* for all terms, term = term % prime  */
        
        Map<Integer,Integer> map = this.terms;
        
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            int exp = entry.getKey();
            int coef = entry.getValue();
            
            coef = coef % modulus;
        
            /* Error checking for negative modulus */
            if (coef < 0){
                coef += modulus;
            }
            map.put(exp,coef);
        }
        this.terms = map;
    }
    
    public String parsePoly(){
        String output = "";
        Map<Integer, Integer> a_terms = this.getTerms();
        List<String> print = new ArrayList();
        
        for (Map.Entry<Integer,Integer> entry: a_terms.entrySet()){
            String exp = entry.getKey().toString();
            String coef = entry.getValue().toString();  
            output = output + exp+"^X"+coef+" + ";
        }
        output = output.substring(0,output.length()-3);
//      System.out.println(output);
        String reverse = new StringBuffer(output).reverse().toString();
        return reverse;
    }

    /* Accessor functions */
    public Map<Integer, Integer> getTerms() {
        return this.terms;
    }

    public void setTerms(Map<Integer, Integer> a_terms) {
        this.terms = new HashMap(a_terms);
        this.updateModulo();
    }
    
    public void changeModulo(int n){
        this.modulus = n;
        this.updateModulo();
    }

    public Polynomial add(Polynomial p) {
        Map<Integer, Integer> poly1 = new HashMap(this.terms);
        Map<Integer, Integer> poly2 = new HashMap(p.getTerms());
        
        Map<Integer,Integer> poly3 = new HashMap();

        /* Adds the coefficients from the second polynomial to the first if the first has such polynomials */
        for (Map.Entry<Integer,Integer> entry: poly1.entrySet()) {
            /* Get the degree for which to add stuff */
            int exp = entry.getKey();

            /* Get the coefficients for both polynomials */
            
            Integer poly1_check = poly1.get(exp); /* TODO if no return, set to 0 */
            if (poly1_check == null){
                poly1_check = 0;
            }
            int poly1_coef = poly1_check;
            
            Integer poly2_check = poly2.get(exp); /* TODO if no return, set to 0 */
            if (poly2_check == null){
                poly2_check = 0;
            } 
            int poly2_coef = poly2_check;
            
            /* Add the coefficient together and store them in poly1_terms */
            poly3.put(exp, poly1_coef + poly2_coef);
        }
        /* takes all of the combined polynomials and overwrites conflicting terms in poly2, leaving added terms together with terms that didnt exist in poly1 */        
        poly2.putAll(poly3);
        Polynomial pReturn = new Polynomial(poly2,this.modulus);
        /* Set the current coefficients to the added coefficients  */
        return pReturn;
    }

    public Polynomial divide(Polynomial p){
        /* TODO */
        return this;
    }
    
    public int[] gcd(Polynomial p){
        /* TODO */
        int[] newArray = new int[]{1,2};
        return newArray;
    }
    
    public void modulo(Polynomial p){
        /* TODO */
    }
    
    public Polynomial multiply(Polynomial p){
        /* TODO */
        Map<Integer,Integer> poly1 = this.getTerms();
        Map<Integer,Integer> poly2 = p.getTerms();       
        /* 1. Create a new Polynomial poly3                                      */
        /* 2. Find the polynomials which you want to multiply as poly1 and poly2 */

        Map<Integer,Integer> poly3 = new HashMap();

        for (int order1: poly1.keySet()) {
            for (int order2 : poly2.keySet()) {
                /* Get the coefficients that go with the orders */
                Integer exponent = order1 + order2;
                Integer coefficient =  poly1.get(order1) * poly2.get(order2);
                if (poly3.containsKey(exponent)) {
                    coefficient += poly3.get(exponent);
                } 
		poly3.put(exponent, coefficient);
            }
        }
        this.updateModulo();
        return this;
    }
    
    public Polynomial scalar(int s){
        /* Done */
        Map<Integer,Integer> scalar = new HashMap(this.terms);
        
        for(Map.Entry<Integer, Integer> entry: scalar.entrySet()){
            int exp = entry.getKey();
            int coef = entry.getValue();
            scalar.put(exp, coef * s);
        }
        return new Polynomial(scalar,this.modulus);
    }
    
    public Polynomial subtract(Polynomial p){
        /* TODO */
        Map<Integer, Integer> poly1 = this.getTerms();
        Map<Integer, Integer> poly2 = p.getTerms();

        /* Subtracts the coefficients from the second polynomial to the first if the first has such polynomials */
        for (Map.Entry<Integer,Integer> entry: poly1.entrySet()) {
            /* Get the degree for which to add stuff */
            int key = entry.getKey();

            /* Get the coefficients for both polynomials */
            
            Integer poly1_check = poly1.get(key); /* TODO if no return, set to 0 */
            if (poly1_check == null){
                poly1_check = 0;
            }
            int poly1_coef = poly1_check;
            
            Integer poly2_check = poly2.get(key); /* TODO if no return, set to 0 */
            if (poly2_check == null){
                poly2_check = 0;
            } 
            int poly2_coef = poly2_check;
            
            /* Subtracts the coefficients and store them in poly1_terms */
            poly1.put(key, poly1_coef - poly2_coef);
        }
        /* takes all of poly1 and overwrites conflicting terms in poly2, leaving non-conflicting terms */
        poly2.putAll(poly1);
        /* Set the current coefficients to the added coefficients  */
        this.setTerms(poly2);
        this.updateModulo();
        return this;
    }
    
    public int[] xGCD(Polynomial p){
        /* TODO */
        int[] newArray = new int[]{1,2};
        return newArray;
    }
    
}
