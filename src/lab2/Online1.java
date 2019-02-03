package lab2;

import java.util.*;
import java.math.*;
import java.io.*;

public class Online1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "_xyz3";
        if (input.length() == 0) {
            System.out.println("No String given");
            return;
        }
        boolean isStartWithunderScoreOrLetter = isUnderScoreOrLetter(input.charAt(0));
        boolean isAllLetterOrUnderScoreorDigit = true;

        for (int i = 1; i < input.length(); i++) {

            if (!isUnderScoreOrLetterOrDigit(input.charAt(i))) {

                isAllLetterOrUnderScoreorDigit = false;
                break;
            }
        }


        if (isStartWithunderScoreOrLetter && isAllLetterOrUnderScoreorDigit) {
            System.out.println("Yes,IDENTIFIER");
        } else {
            System.out.println("Not IDENTIFIER");
        }


    }

    static boolean isUnderScoreOrLetter(char s) {
        if (s == '_' || (s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z')) {
            return true;
        }
        return false;
    }

    static boolean isUnderScoreOrLetterOrDigit(char s) {
        if (s == '_' || (s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z') || (s >= '0' && s <= '9')) {
            return true;
        }
        return false;
    }


}
