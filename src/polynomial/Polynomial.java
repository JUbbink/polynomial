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
    int[][] combined;
    
    public Polynomial(){
        //constructor
    }
    
    public void setOrder(int num){
        order.add(num);
    }
    
    public int getOrder(int num){
        return combined[num][1];
    }
    
    public void setCoefficient(int num){
        coefficient.add(num);
    }
    
    public int getCoefficient(int num){
        return combined[num][0];
    }
    
    public void editCoefficient(int index, int num){
        int old = coefficient.get(index);
        coefficient.set(index, old + num);
    }
    
    public void modCoefficient(int index, int num){
        int old = getCoefficient(index);
        combined[index][0] = old % num;
    }
    //create a 2d array from the 2 arraylist such that the first collumn contains the coefficient and the second collumn the order
    //the array is also sorted on the order from highest to lowest
    //you NEED to call this method otherwise the polynomial won't be created, you will just have 2 unconnected arraylists
    public void create(){
        combined = new int [order.size()][2];
        for (int i = 0; i < order.size(); i++){
            int num = coefficient.get(i);
            combined[i][0] = num;
            num = order.get(i);
            combined[i][1] = num;
        }
        mysort(combined);
    }
    //sorts the newly created array on the order collumn, don't call this method seperately
    public static int[][] mysort(int[][] ar) {
        Arrays.sort(ar, new Comparator<int[]>() {
            @Override
            public int compare(int[] int1, int[] int2) {
                Integer numOfKeys1 = int1[1];
                Integer numOfKeys2 = int2[1];
                return numOfKeys2.compareTo(numOfKeys1);
            }
        });
        return ar;
    }
}