/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomial;

import java.lang.Math;

/**
 *
 * @author Jeroen
 */
public class Calculator {
    
    //input: polynomial a, polynomial b
    //output: polynomial c where c = a+b
    public Polynomial addition(Polynomial poly1, Polynomial poly2){
        
        Polynomial add = new Polynomial();
        /*
        
        fucked this up completely
        
        int difference = Math.abs(poly1.combined.length - poly2.combined.length);
        
        if (poly1.combined.length > poly2.combined.length){
            
            for(int i = 0; i < difference; i++){
                add.setOrder(poly1.getOrder(i));
                add.setCoefficient(poly1.getCoefficient(i));
            }
            for(int i = 0; i < poly1.combined.length-difference; i++){            
                add.setOrder(poly1.getOrder(i+difference) + poly2.getOrder(i));
                add.setCoefficient(poly1.getCoefficient(i+difference) + poly2.getCoefficient(i));
            }
        }
        else if(poly1.combined.length < poly2.combined.length){
            
            for(int i = 0; i < difference; i++){
                add.setOrder(poly2.getOrder(i));
                add.setCoefficient(poly2.getCoefficient(i));
            }
            for(int i = 0; i < poly2.combined.length-difference; i++){            
                add.setOrder(poly2.getOrder(i+difference) + poly1.getOrder(i));
                add.setCoefficient(poly2.getCoefficient(i+difference) + poly1.getCoefficient(i));
            }
        }
        else{
            System.out.println("ERROR, polynomials undefined");
        }
        */
        if (poly1.combined.length > poly2.combined.length){
            for(int i = 0; i < poly1.combined.length; i++){
                for(int x = 0; x<poly1.combined.length; x++){
                    if (poly1.getOrder(i) == poly2.getOrder(x)){
                        add.setCoefficient(poly1.getCoefficient(i)+poly2.getCoefficient(x));
                        add.setOrder(poly1.getOrder(i));
                    }
                }
            }
        }
        else if(poly1.combined.length < poly2.combined.length){
            for(int i = 0; i < poly2.combined.length; i++){
                for(int x = 0; x<poly2.combined.length; x++){
                    if (poly2.getOrder(i) == poly1.getOrder(x)){
                        add.setCoefficient(poly2.getCoefficient(i)+poly1.getCoefficient(x));
                        add.setOrder(poly2.getOrder(i));
                    }
                }
            }
        }
        else{
            System.out.println("ERROR, polynomials undefined");
        }
        
        add.create();
        return add;
    }
    
    
}
