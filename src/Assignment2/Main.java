package Assignment2;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(new File("F:\\CSE\\4.1\\CompilarLab\\src\\Assignment2\\input.c"));
            BufferedReader br = new BufferedReader(fr);
            fw = new FileWriter(new File("F:\\CSE\\4.1\\CompilarLab\\src\\Assignment2\\output.txt"));

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

            String temporaryString = getCodeWithoutExtraSpace(codeWithout_newline_tab);


            String[] anArray = new String[]{"[","]","{","}","(",")","==","<=",">=","!=","+=","-=","*=","/=","++","--",";",",","'"};
            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(anArray));
                //"[","]","{","}","(",")"
                ///"+","-","*","/"

            for (String aString:anArray) {
                if(aString == ";" || aString ==","){
                    temporaryString = replaceString(temporaryString,aString," "+aString);
                }
                else{
                    temporaryString = replaceString(temporaryString,aString," "+aString+" ");
                }

            }

          String[] splittedString = temporaryString.split(" ");

            String finalString = "";
            for(String aString:splittedString){
                String[] singleCharacterStrings = new String[]{"+","-","*","/","="};
                ArrayList<String> singleCharacterStringList = new ArrayList<String>(Arrays.asList(singleCharacterStrings));

                if(aString.trim().length() ==0){
                    continue;
                }
                if(!myList.contains(aString)){

                    for (String aSngleCharaterString:singleCharacterStringList){
                        aString = replaceString(aString,aSngleCharaterString," "+aSngleCharaterString+" ");
                    }
                }
                finalString += aString.trim()+" ";
            }

            System.out.println("Final String");
            System.out.println(finalString);
            String[] tokens = finalString.split(" ");

            String finalOutput = "";
            for(String aString: tokens){
                if(aString.length() ==0){

                }
                else{
                    finalOutput += "["+getCategory(aString)+" "+aString+"] ";

                }
            }


            //char c ; int x1 , x_2 ; float y1 , y2 ; x1=5 ; x_2= 10 ; y1=2.5+x1*45 ; y2=100.o5-x_2/3 ; if(y1 <= y2) c='y' ; else c='n' ;
            fw.write(finalOutput.trim());

            System.out.println("The Input File Code is:");
            System.out.println(inputCode);
            System.out.println("The Output File Code is:");
            System.out.println(finalOutput.trim());


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

    public static String replaceString(String aFullString,String target,String replace){
        return aFullString.replace(target,replace);
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

    public static String getCategory(String s) {
        Set<String> keyword = new HashSet<>();
        keyword.add("char");
        keyword.add("int");
        keyword.add("float");
        keyword.add("if");
        keyword.add("else");
        keyword.add("#include<stdio.h>");
        //keyword.add("main");
        keyword.add("void");
        keyword.add("printf");
        keyword.add("break");
        keyword.add("long");
        keyword.add("switch");
        keyword.add("case");
        keyword.add("enum");
        keyword.add("register");
        keyword.add("extern");
        keyword.add("return");
        keyword.add("union");
        keyword.add("const");
        keyword.add("short");
        keyword.add("unsigned");
        keyword.add("continue");
        keyword.add("for");
        keyword.add("signed");
        keyword.add("default");
        keyword.add("goto");
        keyword.add("sizeof");
        keyword.add("volatile");
        keyword.add("do");
        keyword.add("static");
        keyword.add("while");

        Set<String> seperator = new HashSet<>();
        seperator.add(";");
        seperator.add(",");
        seperator.add("'");
        seperator.add("\"");

        Set<String> operator = new HashSet<>();
        operator.add("+");
        operator.add("-");
        operator.add("*");
        operator.add("/");
        operator.add("=");
        operator.add("<=");
        operator.add(">=");

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
