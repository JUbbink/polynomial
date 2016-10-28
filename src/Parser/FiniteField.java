/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import static java.lang.Math.floor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeroen
 */
public class FiniteField {

    /*Modulo of the field*/
    private int modulo;

    /*Max Degree of the field*/
    private int deg;

    /* Irreducible polynomial */
    private Polynomial generator;

    /* List of Polynomials within the field */
    private List elements;

    /*Constructor*/
    public FiniteField(Polynomial g) { //DOES NOT CHECK IF g IS REDUCIBLE
        generator = g;
        modulo = g.getMod();
        deg = g.getMaxTerm();
        elements = new ArrayList<>();

        for (int i = 0; i < Math.pow(modulo, deg); i++) {
            Polynomial element = new Polynomial(modulo);
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

    /*List of Methods*/
    //NOT DONE
    public String additionTable() {
        String topleft = new String();
        topleft = topleft + "+";
        int longestString = topleft.length();
        for (int i = 0; i < elements.size(); i++) {
            int length = elements.get(i).toString().length();
            if (length > longestString) {
                longestString = length;
            }
        }
        int lineLength = (elements.size() + 1) * longestString + elements.size();
        String output = new String();
        output += topleft;
        for (int i = 0; i < elements.size(); i++) {
            output += "|" + elements.get(i).toString();
        }
        output = output + "\n";
        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < lineLength; j++) {
                output += "-";
            }
            output = output + "\n";
            output = output + elements.get(i).toString();
            for (int j = 0; j < elements.size(); j++) {
                output += "|" + sum((Polynomial) elements.get(i), 
                        (Polynomial) elements.get(j)).toString();
            }
            output += "\n";
        }
        return output;
    }

    //NOT DONE
    public String multiplicationTable() {
        String topleft = new String();
        topleft = topleft + "*";
        int longestString = topleft.length();
        for (int i = 0; i < elements.size(); i++) {
            int length = elements.get(i).toString().length();
            if (length > longestString) {
                longestString = length;
            }
        }
        int lineLength = (elements.size() + 1) * longestString + elements.size();
        String output = new String();
        output += topleft;
        for (int i = 0; i < elements.size(); i++) {
            output += "|" + elements.get(i).toString();
        }
        output = output + "\n";
        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < lineLength; j++) {
                output += "-";
            }
            output = output + "\n";
            output = output + elements.get(i).toString();
            for (int j = 0; j < elements.size(); j++) {
                output += "|" + product((Polynomial) elements.get(i), 
                        (Polynomial) elements.get(j)).toString();
            }
            output += "\n";
        }
        return output;
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

    //DONE
    public boolean isPrimitive(Polynomial p) {
        //TODO Add robustness
        if (p.getMaxTerm() <= 0) {
            return false;
        }
        int m = p.getMod();
        int o = (int) Math.pow(m, this.deg);
        List divisors = primeDivisors(o - 1);
        int len = divisors.size();

        int i = 0;
        Polynomial bla = exponentiate(p, (o - 1) / (int) divisors.get(i));
        while ((i < len) && !(exponentiate(p, (o - 1) / 
                (int) divisors.get(i)).equals(new Polynomial().makeUnit(m)))) {
            i++;
        }
        return i >= len;
    }

    private Polynomial exponentiate(Polynomial p, int modulus) {
        /* Get a local copy of the polynomial p */
        Polynomial f = new Polynomial();
        try {
            f = p.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(ex.toString());
        }
        Polynomial g = new Polynomial().makeUnit(modulus);

        /* Get the number of moduli by doing a log_2(modulus) */
        int nof_moduli = (int) floor(Math.log(modulus) / Math.log(2) + 1);

        for (int i = 0; i < nof_moduli; i++) {
            /* Square f */
            if (i != 0) {
                f = product(f, f);
            }

            /* Multiply g by f */
            if (((modulus >> i) & 1) != 0) {
                g = product(g, f);
            }
        }
        return g;
    }

    //DONE
    public Polynomial primitive() {
        int r = (int) (Math.random() % this.elements.size());
        while (!isPrimitive((Polynomial) this.elements.get(r))) {
            r = (int) (Math.random() % this.elements.size());
        }
        return (Polynomial) this.elements.get(r);
    }

    /**
     * Algorithm 2.3.3 From Reader, finding inverses
     *
     * @param p the Polynomial you wish to invert
     * @return the inverse of the Polynomial p
     */
    private Polynomial inverse(Polynomial p) {
        //TODO Add checking if p is in finite field, AND some error polynomial

        int m = this.generator.getMod();
        Polynomial[] output = this.generator.xGCD(p);

        Map terms = new HashMap();
        terms.put(0, 1);
        Polynomial unitPoly = new Polynomial(terms, m);
        if (output[0] == unitPoly) {
            Polynomial[] poly = output[0].divide(this.generator);
            if (elements.contains(poly[1])) {
                return poly[1];
            }
        }
        return new Polynomial();//THIS NEEDS TO CHANGE
    }

    private List<Integer> primeDivisors(int p) {
        List result = new ArrayList<>();
        for (int i = 2; i <= p; i++) {
            if ((p % i) == 0 && isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isPrime(int i) {
        if (i <= 1) {
            return false;
        }
        if (i <= 3) {
            return true;
        }
        if ((i % 2 == 0) || (i % 3 == 0)) {
            return false;
        }
        for (int n = 5; n * n <= i; n += 6) {
            if ((i % n == 0) || (i % (n + 2)) == 0) {
                return false;
            }
        }
        return false;
    }
}
