package Assignmnt4;

import java.util.*;
import java.math.*;
import java.io.*;

//w=a-b*c/d+e-f
///a=b*-c+b*-c
public class TreeAddressCodeGeneration {

    static int counter = 0;
    static Map<String, String> myMap = new TreeMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();

        List<Character> operators = new ArrayList<Character>(Arrays.asList(new Character[]{'/', '*','+', '-' }));
        String output = getSingleOperatorString(expression);
        System.out.println(output);
        System.out.println(myMap);
        System.out.println();

        for (Character operator: operators){
                output = replaceOperator(output,operator);
          //  System.out.println(output);
        }

        //System.out.println(myMap);
        FileWriter fw=null;
        try {
             fw = new FileWriter(new File("F:\\CSE\\4.1\\CompilarLab\\src\\Assignmnt4\\treeAddressCode.txt"));

            Iterator<String> it = myMap.keySet().iterator();
            while (it.hasNext()){
                String temp= it.next();
                String out =temp +"="+myMap.get(temp);
                fw.append(out+"\n");
            }
            fw.close();
        }
        catch (Exception e){
            System.out.println("File Error");
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String replaceOperator(String expression, Character operator) {

        String myString = "";
        boolean isPosLenOne = true;
        String replaceWith = null;
        List<Integer> positons = getPositionOfOperator(expression, operator);

        ArrayList<String> tokenList = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            tokenList.add(Character.toString(expression.charAt(i)));
        }



        for (Integer i : positons) {

            String leftString = "";
            String rightString = "";
            int temp = i - 1;
            while (temp >= 0) {
                if (tokenList.get(temp).equals("=") || tokenList.get(temp).equals("+") || tokenList.get(temp).equals("-") || tokenList.get(temp).equals("*") || tokenList.get(temp).equals("/")) {
                    break;
                }
                leftString += tokenList.get(temp);
                tokenList.set(temp, "");
                temp--;
            }

            temp = i + 1;
            while (temp < tokenList.size()) {
                if (tokenList.get(temp).equals("=") || tokenList.get(temp).equals("+") || tokenList.get(temp).equals("-") || tokenList.get(temp).equals("*") || tokenList.get(temp).equals("/")) {
                    break;
                }

                rightString += tokenList.get(temp);
                tokenList.set(temp, "");
                temp++;

            }
            if(!myMap.containsKey(leftString)){
                leftString = new StringBuilder(leftString).reverse().toString();
            }

            String address = leftString + operator.toString() + rightString;

             replaceWith = "t" + counter;
            myMap.put(replaceWith, address);

            tokenList.set(i, replaceWith);
            tokenList.set(i - 1, "");
            tokenList.set(i + 1, "");
            counter++;

            if(positons.size()>1){
                myString ="";
                isPosLenOne = false;
               // System.out.println("address "+address);
                for (String s : tokenList) {
                    myString += s;
                }
                if(replaceWith!=null){
                    System.out.println(replaceWith+"="+myMap.get(replaceWith)+" "+myString);
                }

            }


        }
        if(isPosLenOne){
            for (String s : tokenList) {
                myString += s;
            }
            if(replaceWith!=null){
                System.out.println(replaceWith+"="+myMap.get(replaceWith)+" "+myString);
            }

        }


        return myString;
    }

    public static String getSingleOperatorString(String s) {
        String myString = "";

        List<Integer> myList = getPositionOfDoubleOperator(s);

        for (int i = 0; i < s.length(); i++) {
            if (myList.contains(i)) {
                String replaceString = Character.toString(s.charAt(i)) + Character.toString(s.charAt(i + 1));

                String replaceWith = "t" + counter;
                myString += (replaceWith);
                myMap.put(replaceWith, replaceString);
                counter++;

            } else {
                if (i > 0 && myList.contains(i - 1)) {

                } else {
                    myString += s.charAt(i);
                }
            }
        }
        return myString;
    }

    public static List<Integer> getPositionOfDoubleOperator(String s) {
        Set<Character> operator = new HashSet<>();
        operator.add('+');
        operator.add('-');
        operator.add('*');
        operator.add('/');
        List<Integer> myList = new ArrayList<>();
        for (int i = 0; i < s.length() - 1; i++) {
            if (operator.contains(s.charAt(i)) && operator.contains(s.charAt(i + 1))) {
                myList.add(i + 1);
            }
        }

        return myList;
    }

    public static List<Integer> getPositionOfOperator(String s, Character operator) {
        List<Integer> myList = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == operator) {
                myList.add(i);
            }
        }

        return myList;
    }
}
