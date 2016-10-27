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
    
    /** Irreducible polynomial **/
    private Polynomial generator;
    
    /** List of Polynomials within the field **/
    private List elements;
    
    //list of methods
    
    /*Constructor*/
    public FiniteField(Polynomial g){
        generator = g;
        modulo = g.getMod();
        deg = g.getMaxTerm();
        
    }
     
    public String additionTable(){
        
    }
    
    public String multiplicationTable(){
        
    }
    
    public Polynomial sum(Polynomial p1, Polynomial p2){
        
    }
    
    public Polynomial product(Polynomial p1, Polynomial p2){
        
    }
    
    public Polynomial quotient(Polynomial p1, Polynomial p2){
        
    }
    
    public boolean isPrimitive(Polynomial p){
        
    }
    
    public Polynomial primitive(){
        
    }
}
