
package mid;
//Abv 7.8 45 234.2 0.7 .5 a5
import java.io.*;
import java.util.*;
import java.math.*;
public class Mid {

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      String s = sc.nextLine();
      String arr[] = s.split(" ");
      
      int count = 0;
      for(String a: arr){
          if(isnumber(a)){
              count++;
          }
      }
        System.out.println("Total Number Count ="+count);
    }

    private static boolean isnumber(String a) {
       if(isContainOnlyNumberAndDot(a)){
           if(numberOfDots(a)==0 || numberOfDots(a)==1){
               return true;
           }
           else{
               return false;
            }
       }
       else{
           return false;
       }
    }

    private static boolean isContainOnlyNumberAndDot(String a) {
       for(int  i=0;i<a.length();i++){
           if((a.charAt(i)>='0' && a.charAt(i)<='9')||(a.charAt(i)=='.')){
               
           }
           else{
               return false;
           }
       }
       
       return true;
    }

    private static int numberOfDots(String a) {
        int count = 0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)=='.'){
                count++;
            }
        }
        
       return count;
    }
    
}
