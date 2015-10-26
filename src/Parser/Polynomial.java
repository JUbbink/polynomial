package Parser;
import java.util.HashMap;
import java.util.Map;


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
    
    public Polynomial(Map<Integer,Integer> terms , int m){
        this.terms = terms;
        this.modulus = m;
    }
    
    public Polynomial(Map<Integer,Integer> terms){
        this.terms = terms;
        this.modulus = 1;
    }
    
    public Polynomial(){
        Map<Integer,Integer> newMap = new HashMap();
        
        this.terms=newMap;
        this.modulus = 1;
    }

    /* Re-modulates all terms, to be called after every change */
    private void updateModulo() {
        /* for all terms, term = term % prime  */
        
        for(Map.Entry<Integer,Integer> entry: terms.entrySet()){
            int key = entry.getKey();
            int val = entry.getValue() % modulus;
        
            /* Error checking for negative modulus */
            if (val < 0){
                val += modulus;
            }
        terms.put(key,val);
        }
    }
    
    public String parsePoly(){
        String output = "";
        Map<Integer, Integer> a_terms = this.getTerms();
        
        for (Map.Entry<Integer,Integer> entry: a_terms.entrySet()){
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();  
            output = output + key + "X^" + value + " + ";  
        } 
//      System.out.println(output);
        return output;
    }

    /* Accessor functions */
    public Map<Integer, Integer> getTerms() {
        return this.terms;
    }

    public void setTerms(Map<Integer, Integer> a_terms) {
        this.terms = a_terms;
    }
    
    public void changeModulo(int n){
        this.modulus = n;
        this.updateModulo();
    }

    public Polynomial add(Polynomial p) {
        Map<Integer, Integer> poly1 = this.getTerms();
        Map<Integer, Integer> poly2 = p.getTerms();

        /* Adds the coefficients from the second polynomial to the first if the first has such polynomials */
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
            
            /* Add the coefficient together and store them in poly1_terms */
            poly1.put(key, poly1_coef + poly2_coef);
        }
        /* takes all of poly1 and overwrites conflicting terms in poly2, leaving non-conflicting terms */        
        poly2.putAll(poly1);
        /* Set the current coefficients to the added coefficients  */
        this.setTerms(poly2);
        this.updateModulo();
        return this;
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
    void modulo(Polynomial p){
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
        /* TODO */
        Map<Integer,Integer> scalar = this.getTerms();
        
        for(Map.Entry<Integer, Integer> entry: scalar.entrySet()){
            int key = entry.getKey();
            terms.put(key, scalar.get(key) * s);
        }
        this.terms = scalar;
        this.updateModulo();
        return this;
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
