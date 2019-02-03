
package lab2;

import java.util.*;
import java.math.*;
import java.io.*;

public class Online2 {

    public static void main(String[] args) {
        String s = ".389";
        if (s.length() == 0) {
            System.out.println("Not Numeric Constant");
        } else if (checkAllDigit(s)) {
            System.out.println("Yes Numeric Constant");
        } else {
            if (isContainOneDot(s)) {
                String left = "";
                String right = "";
                for (int i = 0; i < s.indexOf("."); i++) {
                    left += s.charAt(i);
                }

                for (int i = s.indexOf(".") + 1; i < s.length(); i++) {
                    right += s.charAt(i);
                }


                if (checkAllDigit(left) && checkAllDigit(right)) {
                    System.out.println("Yes Numeric Constant");

                } else if (left.length() == 0 && right.length() > 0) {
                    if (checkAllDigit(right)) {
                        System.out.println("Yes Numeric Constant");
                    } else {
                        System.out.println("Not Numeric Constant");
                    }
                } else {
                    System.out.println("Not Numeric Constant");
                }

            } else {
                System.out.println("Not Numeric Constant");
            }
        }

    }

    static boolean checkAllDigit(String s) {
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {

            } else {
                return false;
            }
        }

        return true;
    }

    static boolean isContainOneDot(String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                counter++;
            }

        }

        return counter > 0;
    }

    static int numberOfDigit(String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                counter++;
            }

        }

        return counter;
    }

}
