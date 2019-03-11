package lab3;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab3 {

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader(new File("F:\\CSE\\4.1\\CompilarLab\\src\\lab3\\input.txt"));
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(new File("F:\\CSE\\4.1\\CompilarLab\\src\\lab3\\outputlab.txt"));
            String s = "";
            String code = "";
            while ((s = br.readLine()) != null) {
                code += s + " ";
            }
            code = code.trim();

            //System.out.println(code);
            String[] lexeme = code.split(" ");
            //System.out.println(lexeme.length);
            String output = "";
            for (String myString : lexeme) {
                ///System.out.println(myString + " " + getCategory(myString));
                output += "[" + getCategory(myString) + " " + myString + "]";
            }

            fw.write(output);
            fw.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lab3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lab3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getCategory(String s) {
        Set<String> keyword = new HashSet<>();
        keyword.add("char");
        keyword.add("int");
        keyword.add("float");
        keyword.add("if");
        keyword.add("else");

        Set<String> seperator = new HashSet<>();
        seperator.add(";");
        seperator.add(",");
        seperator.add("'");

        Set<String> operator = new HashSet<>();
        operator.add("+");
        operator.add("-");
        operator.add("*");
        operator.add("/");
        operator.add("=");
        operator.add("<=");

        Set<String> parantesis = new HashSet<>();
        parantesis.add("(");
        parantesis.add(")");
        parantesis.add("[");
        parantesis.add("]");
        parantesis.add("{");
        parantesis.add("}");

        if (keyword.contains(s)) {
            return "kw";
        } else if (seperator.contains(s)) {
            return "sep";
        } else if (operator.contains(s)) {
            return "op";
        } else if (parantesis.contains(s)) {
            return "par";
        } else if (isNumber(s)) {
            return "num";
        } else if (isIdentifier(s)) {
            return "id";
        } else {
            return "unkn";
        }

    }

    public static boolean isNumber(String s) {
        if (s.length() == 0) {
            return false;
        }
        int pointCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                pointCount++;
            }
        }

        if (pointCount > 1) {
            return false;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '.') {

                } else {
                    return false;
                }
            }
        }
        return true;

    }

    public static boolean isIdentifier(String s) {

        if (s == null || s.length() == 0) {
            return false;
        }

        char[] c = s.toCharArray();
        if (!Character.isJavaIdentifierStart(c[0])) {
            return false;
        }

        for (int i = 1; i < c.length; i++) {
            if (!Character.isJavaIdentifierPart(c[i])) {
                return false;
            }
        }

        return true;
    }


}
