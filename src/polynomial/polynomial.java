/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomial;

import java.util.ArrayList;

/**
 *
 * @author s127855
 */
public class polynomial {
    ArrayList<Integer> order = new ArrayList<Integer>();
    ArrayList<Integer> quotient = new ArrayList<Integer>();
    int[][] polynomial;
    
    public void addOrder(int num){
        order.add(num);
    }
    
    public void addQuotient(int num){
        quotient.add(num);
    }
    public void create(ArrayList a, ArrayList b){
        polynomial = new int [a.size()][2];
        for (int i = 0; i < a.size(); i++){
            int num = Integer.parseInt(a.get(i).toString());
            polynomial[i][0] = num;
            num = Integer.parseInt(b.get(i).toString());
            polynomial[i][1] = num;
        }
    }
}

