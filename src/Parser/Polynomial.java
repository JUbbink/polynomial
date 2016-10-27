package Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;


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
        Map<Integer, Integer> newMap = new HashMap();
        newMap.put(1, 1);

        this.terms = newMap;
        this.modulus = m;
        this.updateModulo();
    }

    public Polynomial(Map<Integer, Integer> a_terms, int m) {
        this.terms = new HashMap(a_terms);
        this.modulus = m;
        this.updateModulo();
    }

    public Polynomial(Map<Integer, Integer> a_terms) {
        this.terms = new HashMap(a_terms);
        this.modulus = Integer.MAX_VALUE;//max value of an int
        this.updateModulo();
    }

    public Polynomial() {
        Map<Integer, Integer> newMap = new HashMap();
        newMap.put(1, 1);

        this.terms = newMap;
        this.modulus = Integer.MAX_VALUE;//max value of an int
    }

    /* Re-modulates all terms, to be called after every change */
    private void updateModulo() {
        /* for all terms, term = term % prime  */

        for (Map.Entry<Integer, Integer> entry : this.terms.entrySet()) {
            int coef = entry.getValue();
            entry.setValue(IntegerModP.modulo(coef, modulus));
        }
    }

    public int getMod() {
        return this.modulus;
    }

    public int getMaxTerm() {
        return deg(this);
    }
    
    /**
     * Returns a human readable string representation of the polynomial
     * 
     * @return String representation of the polynomial in the form a1X^c1 
     * for all a1 in poly.getTerms.getValue and all c1 in poly.getTerms.getKey
     * the String in printed in ascending order of powers
     */
    public String parsePoly(){
        String output = "";

        //Converts the hashmap of orders and coefficients to a treemap, which is ordered 
        //on the key.
        Map<Integer, Integer> a_terms = new TreeMap<Integer, Integer>(this.getTerms());
        
        for (Map.Entry<Integer,Integer> entry: a_terms.entrySet()){
            String exp = entry.getKey().toString();
            String coef = entry.getValue().toString();  
            output = output + coef + "X^" + exp + " + ";
        }
        output = output.substring(0,output.length()-3);

        return output;
    }

    /* Accessor functions */
    public Map<Integer, Integer> getTerms() {
        return this.terms;
    }

    public void setTerms(Map<Integer, Integer> a_terms) {
        this.terms = new HashMap(a_terms);
        this.updateModulo();
    }

    public void setModulo(int n) {
        this.modulus = n;
        this.updateModulo();
    }

    public Polynomial add(Polynomial p) {
        Map<Integer, Integer> poly1 = new HashMap(this.terms);
        Map<Integer, Integer> poly2 = new HashMap(p.getTerms());

        Map<Integer, Integer> poly3 = new HashMap();

        int newMod = Math.max(this.modulus, p.modulus);


        /* Adds the coefficients from the second polynomial to the first if the first has such polynomials */
        for (Map.Entry<Integer, Integer> entry : poly1.entrySet()) {
            /* Get the degree for which to add stuff */
            int exp = entry.getKey();

            /* Get the coefficients for both polynomials */
            Integer poly1_check = poly1.get(exp);
            /* TODO if no return, set to 0 */
            if (poly1_check == null) {
                poly1_check = 0;
            }
            int poly1_coef = poly1_check;

            Integer poly2_check = poly2.get(exp);
            /* TODO if no return, set to 0 */
            if (poly2_check == null) {
                poly2_check = 0;
            }
            int poly2_coef = poly2_check;

            /* Add the coefficient together and store them in poly1_terms */
            poly3.put(exp, poly1_coef + poly2_coef);
        }
        /* takes all of the combined polynomials and overwrites conflicting terms in poly2, leaving added terms together with terms that didnt exist in poly1 */
        poly2.putAll(poly3);
        /* Set the current coefficients to the added coefficients  */
        return new Polynomial(poly2, newMod);
    }

    public Polynomial[] divide(Polynomial b) {
        /* Algorithm 1.2.6, Long Division
        Input: Poly a, b != 0
        Output: quot (a,b), rem(a,b)
        -----------------------------
        Step1 - q = 0, r = a
        Step2 - while deg(r)>=deg(b) do
                |   q=q+val(r)/val(b)*X^exp(r)-exp(b)
                |   r=r-val(r)/val(b)*X^exp(r)-exp(b)*b
        Step3 - output q,r
         */
        int p = this.getMod();
        Polynomial q = new Polynomial(p);
        Polynomial r = new Polynomial(this.getTerms(), p);//set r = a
        while (deg(r) >= deg(b)) {
            Map<Integer, Integer> map = new HashMap();
            int newExp = deg(r) - deg(b);
            int newCoef = IntegerModP.divide(r.getTerms().get(deg(r)), b.getTerms().get(deg(b)), p);
            if (newCoef == -1) {
                return new Polynomial[2];
            }
            map.put(newExp, newCoef);
            Polynomial multiplyPoly = new Polynomial(map, p);
            q = q.add(multiplyPoly);
            r = r.subtract(multiplyPoly.multiply(b));
        }
        Polynomial[] newArray = new Polynomial[2];
        newArray[0] = q;
        newArray[1] = r;
        return newArray;
    }

    private int deg(Polynomial p) {
        Map<Integer, Integer> map = p.getTerms();
        int degree = Integer.MIN_VALUE;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() >= degree && entry.getValue() != 0) {
                degree = entry.getKey();
            }
        }
        return degree;
    }

    public Polynomial gcd(Polynomial p) {
        /* Algorithm 1.2.10, Euclid's algorithm for polynomials
        Input: poly a,b
        Output: gcd a,b
        ----------------
        Step1 - While b!= 0, do 
                |   r = rem(a,b)
                |   a = b;
                |   b = r;
        Step2 - Output a
         */
        Polynomial a = new Polynomial(this.getTerms(), this.getMod());
        Polynomial b = new Polynomial(p.getTerms(), p.getMod());

        while (!b.areAllCoeffZero()) {
            Polynomial[] longDiv = a.divide(b);
            Polynomial r = new Polynomial(longDiv[0].getTerms(), longDiv[0].getMod());
            a = b;
            b = r;
        }
        return a;
    }

    public Polynomial multiply(Polynomial p) {
        Map<Integer, Integer> poly1 = new HashMap(this.getTerms());
        Map<Integer, Integer> poly2 = new HashMap(p.getTerms());
        /* 1. Create a new Polynomial poly3                                      */
 /* 2. Find the polynomials which you want to multiply as poly1 and poly2 */

        int newMod = Math.max(this.modulus, p.modulus);
        Map<Integer, Integer> poly3 = new HashMap();

        for (Map.Entry<Integer, Integer> entry1 : poly1.entrySet()) {
            int expA = entry1.getKey();
            int coefA = entry1.getValue();
            for (Map.Entry<Integer, Integer> entry2 : poly2.entrySet()) {
                int expB = entry2.getKey();
                int coefB = entry2.getValue();

                int newExp = expA + expB;
                int newCoef = 0;
                if (poly3.containsKey(newExp)) {
                    newCoef = poly3.get(newExp);
                }
                poly3.put(newExp, newCoef + coefA * coefB);
            }
        }
        return new Polynomial(poly3, newMod);
    }

    public Polynomial scalar(int s) {
        /* Done */
        Map<Integer, Integer> scalar = new HashMap(this.terms);

        for (Map.Entry<Integer, Integer> entry : scalar.entrySet()) {
            int exp = entry.getKey();
            int coef = entry.getValue();
            scalar.put(exp, coef * s);
        }
        return new Polynomial(scalar, this.modulus);
    }

    public Polynomial subtract(Polynomial p) {
        /* TODO */
        Map<Integer, Integer> poly1 = new HashMap(this.getTerms());
        Map<Integer, Integer> poly2 = new HashMap(p.getTerms());

        /* Subtracts the coefficients from the second polynomial to the first if the first has such polynomials */
        for (Map.Entry<Integer, Integer> entry : poly1.entrySet()) {
            /* Get the degree for which to add stuff */
            int key = entry.getKey();

            /* Get the coefficients for both polynomials */
            Integer poly1_check = poly1.get(key);
            /* if no return, set to 0 */
            if (poly1_check == null) {
                poly1_check = 0;
            }
            int poly1_coef = poly1_check;

            Integer poly2_check = poly2.get(key);
            /* if no return, set to 0 */
            if (poly2_check == null) {
                poly2_check = 0;
            }
            int poly2_coef = poly2_check;

            /* Subtracts the coefficients and store them in poly1_terms */
            poly1.put(key, poly1_coef - poly2_coef);
        }
        /* takes all of poly1 and overwrites conflicting terms in poly2, leaving non-conflicting terms */
        poly2.putAll(poly1);
        /* Set the current coefficients to the added coefficients  */
        Polynomial pReturn = new Polynomial(poly2, this.modulus);
        return pReturn;
    }

    public Polynomial[] xGCD(Polynomial p) {
        /* Algorithm 1.2.11 Extended Euclidean algorithm for polynomials
        Input: poly a,b
        Output: poly x,y with gcd(a,b) = xa+yb
        --------------------------------------
        Step1 - x=1,v=1,y=0,u=0
        Step2 - While b!= 0, do 
                |   q = quot(a,b);
                |   a = b;
                |   b = rem(a,b);
                |   x' = x;
                |   y' = y;
                |   x = u;
                |   y = v;
                |   u = x' - q*u;
                |   v = y' - q*v;
        Step2 - Output x,y
         */

        Polynomial a = new Polynomial(this.getTerms(), this.getMod());
        Polynomial b = new Polynomial(p.getTerms(), p.getMod());

        Map<Integer, Integer> map1 = new HashMap();
        Map<Integer, Integer> map2 = new HashMap();
        map1.put(0, 1);
        map2.put(0, 0);

        Polynomial x = new Polynomial(map1, this.getMod());
        Polynomial v = new Polynomial(map1, this.getMod());
        Polynomial y = new Polynomial(map2, this.getMod());
        Polynomial u = new Polynomial(map2, this.getMod());
        Polynomial xPrime = new Polynomial(this.getMod());
        Polynomial yPrime = new Polynomial(this.getMod());

        while (!b.areAllCoeffZero()) {
            Polynomial[] longDiv = a.divide(b);
            Polynomial q = new Polynomial(longDiv[1].getTerms(), longDiv[1].getMod());
            a = b;
            b = new Polynomial(longDiv[0].getTerms(), longDiv[0].getMod());
            xPrime = x;
            yPrime = y;
            x = u;
            y = v;
            u = xPrime.subtract(q.multiply(u));
            v = yPrime.subtract(q.multiply(v));
        }
        Polynomial[] output = new Polynomial[2];
        output[0] = x;
        output[1] = y;
        return output;
    }

    public boolean areAllCoeffZero() {
        for (Map.Entry<Integer, Integer> entry : this.terms.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }
}