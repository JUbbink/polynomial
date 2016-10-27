/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jeroen
 */
public class FiniteField {

    /*Modulo of the field*/
    private int modulo;

    /*Max Degree of the field*/
    private int deg;

    /**
     * Irreducible polynomial *
     */
    private Polynomial generator;

    /**
     * List of Polynomials within the field *
     */
    private List elements;

    //list of methods
    /*Constructor*/
    public FiniteField(Polynomial g) { //DOES NOT CHECK IF g IS REDUCIBLE
        generator = g;
        modulo = g.getMod();
        deg = g.getMaxTerm();

        for (int i = 0; i < Math.pow(modulo, deg); i++) {
            Polynomial element = new Polynomial();
            element.setModulo(modulo);

            int r = i % modulo;
            int q = i / modulo;
            for (int j = 0; j <= deg; j++) {
                Map testMap = new HashMap();
                testMap.put(r % modulo, j);
                Polynomial test = new Polynomial(testMap, modulo);
                element = element.add(test);
                r = q % modulo;
                q = q / modulo;
            }
            Polynomial[] divisionResult = element.divide(g);
            elements.add(divisionResult[1]);
        }
    }

    //NOT DONE
    public String additionTable() {
        //TODO: Return a string
        return "";
    }

    //NOT DONE
    public String multiplicationTable() {
        return "";
    }
    
    //DONE
    public Polynomial sum(Polynomial p1, Polynomial p2) {
        Polynomial poly = p1.add(p2);
        Polynomial[] result = poly.divide(p2);
        return result[1];
    }
    
    //DONE
    public Polynomial product(Polynomial p1, Polynomial p2) {
        Polynomial poly = p1.multiply(p2);
        Polynomial[] result = poly.divide(p2);
        return result[1];
    }
    
    //DONE
    public Polynomial quotient(Polynomial p1, Polynomial p2) {
        Polynomial result = p1.multiply(inverse(p2));
        return result;

    }
 
    //NOT DONE
    public boolean isPrimitive(Polynomial p) {
        return false;
    }

    //NOT DONE
    public Polynomial primitive() {
        return new Polynomial();
    }

    private Polynomial inverse(Polynomial p) {
        //TODO Add checking if p is in finite field, AND some error polynomial

        int m = this.generator.getMod();
        Polynomial[] output = this.generator.xGCD(p);

        Map terms = new HashMap();
        terms.put(0, 1);
        Polynomial unitPoly = new Polynomial(terms, m);
        if (output[0] == unitPoly) {
            Polynomial[] poly = output[0].divide(this.generator);
             if (elements.contains(poly[1])){
                 return poly[1];
             }
        }
        return new Polynomial();
    }
}
