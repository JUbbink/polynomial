/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.List;

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
                //missing step here
                r = q % modulo;
                q = q / modulo;
            }
            elements.add(element);
        }
    }

    public String additionTable() {
        //TODO: Return a string
        return "";
    }

    public String multiplicationTable() {
        return "";
    }

    public Polynomial sum(Polynomial p1, Polynomial p2) {
        Polynomial result = p1.add(p2);
        //TODO: ADD MODULO SOME POLY
        return new Polynomial();
    }

    public Polynomial product(Polynomial p1, Polynomial p2) {
        Polynomial result = p1.multiply(p2);
        //TODO: ADD MODULO SOME POLY
        return new Polynomial();

    }

    public Polynomial quotient(Polynomial p1, Polynomial p2) {
        Polynomial result = p1.multiply(p2);
        return new Polynomial();

    }

    public boolean isPrimitive(Polynomial p) {
        return false;
    }

    public Polynomial primitive() {
        return new Polynomial();
    }
}
