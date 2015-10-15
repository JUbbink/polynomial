/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author s127855
 */
public class Polynomial {
    ArrayList<Integer> order = new ArrayList<Integer>();
    ArrayList<Integer> coefficient = new ArrayList<Integer>();
    Integer[][] combined;
    
    public Polynomial(){
        //constructor
    }
    
    public void addOrder(int num){
        order.add(num);
    }
    
    public Integer getOrder(int num){
        return combined[num][1];
    }
    
    public void addCoefficient(int num){
        coefficient.add(num);
    }
    
    public Integer getCoefficient(int num){
        return combined[num][0];
    }
    //create a 2d array from the 2 arraylist such that the first collumn contains the coefficient and the second collumn the order
    //the array is also sorted on the order from highest to lowest
    public void create(){
        combined = new Integer [order.size()][2];
        for (int i = 0; i < order.size(); i++){
            int num = Integer.parseInt(coefficient.get(i).toString());
            combined[i][0] = num;
            num = Integer.parseInt(order.get(i).toString());
            combined[i][1] = num;
        }
        mysort(combined);
    }
    public static Integer[][] mysort(Integer[][] ar) {
        Arrays.sort(ar, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] int1, Integer[] int2) {
                Integer numOfKeys1 = int1[1];
                Integer numOfKeys2 = int2[1];
                return numOfKeys2.compareTo(numOfKeys1);
            }
        });
        return ar;
    }
}

