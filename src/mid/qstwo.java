/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mid;

import java.util.Scanner;

public class qstwo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (isAccepted(s)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    private static boolean isAccepted(String s) {
        if (s.length() < 2) {
            return false;
        } else if (s.length() == 2) {
            if (s.charAt(0) == '1' && s.charAt(s.length() - 1) == '1') {
                return true;
            }
        } else {
            if (s.charAt(0) == '1' && s.charAt(s.length() - 1) == '1') {
                for (int i = 1; i < s.length() - 1; i++) {
                    if (s.charAt(i) != '0') {
                       // System.out.println("I "+i);
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
