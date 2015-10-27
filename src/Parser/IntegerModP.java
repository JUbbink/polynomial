/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

/**
 *
 * @author Jeroen
 */
public class IntegerModP {
    public static int[] gcd(int p, int q) {
      if (q == 0)
         return new int[] { p, 1, 0 };
      int[] vals = gcd(q, p % q);
      int d = vals[0];
      int a = vals[2];
      int b = vals[1] - (p / q) * vals[2];
      return new int[] { d, a, b };
   }

    public static int inverse(int k, int p) {
      int[] vals = gcd(k, p);
      int d = vals[0];
      int a = vals[1];
      int b = vals[2];
      if (d > 1) { System.out.println("Inverse does not exist."); return 0; }
      if (a > 0) return a;
      return p + a;
   }
    
   public static int divide(int a, int b, int p) {
       return modulo(a * inverse(b, p), p);
   }
   
   public static int multiply(int a, int b, int p){
       return modulo(a*b,p);
   }
    public static int add(int a, int b, int p){
       return modulo(a+b,p);
   }
    public static int subtract(int a, int b, int p){
       return modulo(a-b,p);
   }
    
    public static int modulo(int a, int p){
        int result = a;
        while(result < 0){
            result += p;
        }
       return result % p;
    }
}
