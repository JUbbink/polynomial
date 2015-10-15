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
        /*
        so version 2 is also fucked, jesus christ
        
        if (poly1.combined.length > poly2.combined.length){
            for(int i = 0; i < poly1.combined.length; i++){
                for(int x = 0; x<poly1.combined.length; x++){
                    if (poly1.getOrder(i) == poly2.getOrder(x)){
                        add.setCoefficient(poly1.getCoefficient(i)+poly2.getCoefficient(x));
                        add.setOrder(poly1.getOrder(i));
                    }
                    else{
                        add.setCoefficient(poly1.getCoefficient(i));
                        add.setOrder(poly1.getOrder(i));
                    }
                }
            }
        }
        else if(poly1.combined.length < poly2.combined.length){
            for(int i = 0; i < poly1.combined.length; i++){
                for(int x = 0; x<poly2.combined.length; x++){
                    if (poly2.getOrder(i) == poly1.getOrder(x)){
                        add.setCoefficient(poly2.getCoefficient(i)+poly1.getCoefficient(x));
                        add.setOrder(poly2.getOrder(i));
                    }
                    else{
                        add.setCoefficient(poly2.getCoefficient(i));
                        add.setOrder(poly2.getOrder(i));
                    }
                }
            }
        }
        else{
            System.out.println("ERROR, polynomials undefined");
        }
        */
        /*
        int count = 0;
        //if the length of both polynomials are the same this 
        if (poly1.combined.length == poly2.combined.length){
            for (int i = 0; i < poly1.combined.length && i < poly2.combined.length; i++){

                if (poly1.getOrder(i) > poly2.getOrder(i)){
                    add.setOrder(poly1.getOrder(i));
                    add.setCoefficient(poly1.getCoefficient(i));                
                }
                else if (poly1.getOrder(i) < poly2.getOrder(i)){
                    add.setOrder(poly2.getOrder(i));
                    add.setCoefficient(poly2.getCoefficient(i));  
                }
                else if (poly1.getOrder(i) == poly2.getOrder(i)){
                    add.setOrder(poly1.getOrder(i));
                    add.setCoefficient(poly1.getCoefficient(i)+ poly2.getCoefficient(i));
                }
                else{
                    System.out.println("ERROR, polynomial undefined");
                }   
            }
            add.create();
            return add;
        }
        if (poly1.getOrder(0) < poly2.getOrder(0)){
            count = poly2.getOrder(0);
        }
        else if (poly1.getOrder(0) > poly2.getOrder(0)){
            count = poly1.getOrder(0);
        }
        int num1 = 0;
        int num2 = 0;
        //Version 3, same as the last 2
        //this loop evaluates whether the exponent as defined in count appears in either array, if it does it adds that exponent to the new polynomial
        //if the exponent as defined in count appears in both arrays
        while( count > 0){
            if(count == poly1.getOrder(num1) && count == poly2.getOrder(num2)){
                add.setOrder(poly1.getOrder(count));
                add.setCoefficient(poly1.getCoefficient(count)+ poly2.getCoefficient(count));
                count--;
                num1++;
                num2++;
            }
            else if(count == poly1.getOrder(num1)){
                add.setOrder(poly1.getOrder(count));
                add.setCoefficient(poly1.getCoefficient(count));
                count--;
                num1++;                    
            }
            else if(count == poly2.getOrder(num2)){
                add.setOrder(poly2.getOrder(count));
                add.setCoefficient(poly2.getCoefficient(count));
                count--;
                num2++; 
            }
            else if(count != poly1.getOrder(num1) && count != poly2.getOrder(num2)){
                count--;
            }
        }tests
                */
        
        for (int i = 0; i < poly1.combined.length; i++){
            add.setOrder(poly1.getOrder(i));
            add.setCoefficient(poly1.getCoefficient(i));
        }
        for (int i = 0; i < poly2.combined.length; i++){
            for(int x = 0; x < poly1.combined.length; x++){
                if (poly2.getOrder(i) == poly1.getOrder(x)){
                    add.editCoefficient(x, poly2.getCoefficient(i));
                    break;
                }
            }
            add.setOrder(poly2.getOrder(i));
            add.setCoefficient(poly2.getCoefficient(i));
        }
        add.create();
        return add;
    }   
}
