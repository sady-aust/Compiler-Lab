package mid;

import java.io.*;
import java.util.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class qsthree {
    public static void main(String[] args) throws IOException {
      FileReader fr;
        try {
            fr = new FileReader(new File("C:\\Users\\student\\Documents\\NetBeansProjects\\Mid\\src\\mid\\input.txt"));
              BufferedReader br = new BufferedReader(fr);
              String myInputString = "";
              String s;
              while ((s =br.readLine())!=null) {                
                  myInputString += s;
            }
              myInputString = myInputString.trim();
              if(myInputString.length()<4){
                  System.out.println("No");
              }
              else{
                  if(myInputString.charAt(0)=='/' && myInputString.charAt(1)=='*' && myInputString.charAt(myInputString.length()-2)=='*' && myInputString.charAt(myInputString.length()-1)=='/'){
                      System.out.println("Yes.it is a multiple Line comment");
                  }
                  else{
                        System.out.println("No");
                  }
              }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(qsthree.class.getName()).log(Level.SEVERE, null, ex);
        }
    
      
        
    }
}
