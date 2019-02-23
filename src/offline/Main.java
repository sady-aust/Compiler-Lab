package offline;

/**
 * A Java Program to remove the comments and white space(extra spaces, tabs and
 * newline characters) from a C source program.
 *
 * @author Md. Toufiqul Islam
 * @ID 15-02-04-097
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(new File("C:\\Users\\student\\Documents\\NetBeansProjects\\Lab3\\src\\offline\\input1.c"));
            BufferedReader br = new BufferedReader(fr);
            fw = new FileWriter(new File("C:\\Users\\student\\Documents\\NetBeansProjects\\Lab3\\src\\offline\\output.txt"));

            String codeWithoutSingleLineComment = "";
            String inputCode = "";

            String myLine = "";
            while ((myLine = br.readLine()) != null) {
                inputCode += myLine + "\n";

                if (myLine.trim().startsWith("//")) {
                    continue;
                } else {
                    codeWithoutSingleLineComment += myLine + "\n";
                }
            }

            String codeWithoutSingleAndMultiLineComment = getCodeWithoutMultilineComment(codeWithoutSingleLineComment);

            String codeWithout_newline_tab = getCodeWithoutNewLineAndTabs(codeWithoutSingleAndMultiLineComment);

            String finalCode = getCodeWithoutExtraSpace(codeWithout_newline_tab);

            fw.write(finalCode.trim());

            System.out.println("The Input File Code is:");
            System.out.println(inputCode);
            System.out.println("The Output File Code is:");
            System.out.println(finalCode.trim());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getCodeWithoutMultilineComment(String aString) {
        String resultString = "";
        for (int i = 0; i < aString.length(); i++) {
            if (aString.charAt(i) == '/' && aString.charAt(i + 1) == '*') {
                i++;
                while (aString.charAt(i) != '/') {
                    i++;
                }
            } else {
                resultString += aString.charAt(i);
            }
        }
        return resultString;
    }

    public static String getCodeWithoutNewLineAndTabs(String aString) {
        String resultString = "";
        for (int i = 0; i < aString.length(); i++) {
            if (aString.charAt(i) == '\n') {
                while (aString.charAt(i) == '\n') {
                    i++;
                    if (i == aString.length()) {
                        break;
                    }
                }
                if (i <= aString.length() - 1) {
                    resultString += " " + aString.charAt(i);
                } else {
                    resultString += " ";
                }

            } else if (aString.charAt(i) == '\t') {
                while (aString.charAt(i) == '\t') {
                    i++;
                    if (i == aString.length()) {
                        break;
                    }
                }

                if (i <= aString.length() - 1) {
                    resultString += " " + aString.charAt(i);
                } else {
                    resultString += " ";
                }

            } else {
                resultString += aString.charAt(i);
            }
        }
        return resultString;
    }

    public static String getCodeWithoutExtraSpace(String aString) {
        String resultString = "";
        for (int i = 0; i < aString.length(); i++) {
            if (aString.charAt(i) == ' ') {
                while (aString.charAt(i) == ' ') {
                    i++;
                    if (i == aString.length()) {
                        break;
                    }
                }

                if (i <= aString.length() - 1) {
                    resultString += " " + aString.charAt(i);
                } else {
                    resultString += " ";
                }

            } else {
                resultString += aString.charAt(i);
            }
        }
        return resultString;
    }
}
