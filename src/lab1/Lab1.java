package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
*   @Author Md. Toufiqul Islam
*  A Java code for formetting a code with line number and removing extra space
* */
public class Lab1 {

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader(new File("F:\\CSE\\4.1\\CompilarLab\\src\\lab1\\datafile1.c"));
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(new File("F:\\CSE\\4.1\\CompilarLab\\src\\lab1\\output1.txt"));

            String s = null;
            String mainS = null;
            int lineNo = 1;
            while ((s = br.readLine()) != null) {
                mainS = Integer.toString(lineNo) + ". " + s;
                //System.out.println(mainS);
                lineNo++;
             //   System.out.println(s.trim().length());
                if (s.trim().length() == 0) {
                    fw.append(mainS+ System.getProperty("line.separator"));
                  //  System.out.println(s);
                } else if (s.trim().length() != 0) {
                    fw.append(getOneString(mainS));
                   // System.out.println(mainS);
                }
            }
            fw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static boolean isAllSpace(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!s.equals(" ")) {
                return true;
            }
        }

        return false;
    }

    static String getOneString(String s) {
        String[] arr = s.split(" ");
        String ans = "";
        for (String a : arr) {
            if (a.trim().length() > 0) {
                ans += a + " ";
            }

        }

        return ans.trim() + System.getProperty("line.separator");
    }

}
