package Assignment3;

import lab3.Lab3;

import java.io.*;
import java.util.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainB {
    static class SymbolTableData {
        int id;
        String indentifierName;
        String identifierType;
        String identifierDataType;
        String scope;

        public SymbolTableData(int id, String indentifierName, String identifierType, String identifierDataType, String scope) {
            this.id = id;
            this.indentifierName = indentifierName;
            this.identifierType = identifierType;
            this.identifierDataType = identifierDataType;
            this.scope = scope;
        }

        public SymbolTableData(String indentifierName, String identifierType, String identifierDataType, String scope) {

            this.indentifierName = indentifierName;
            this.identifierType = identifierType;
            this.identifierDataType = identifierDataType;
            this.scope = scope;
        }
    }

    public static void main(String[] args) {
        try {


            FileReader fr = new FileReader(new File("F:\\CSE\\4.1\\CompilarLab\\src\\Assignment3\\inputB"));
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(new File("F:\\CSE\\4.1\\CompilarLab\\src\\Assignment3\\outputB.txt"));

            String codeWithoutSingleLineComment = "";
            String inputCode = "";

            String myLine = "";
            int count = 1;
            while ((myLine = br.readLine()) != null) {
                inputCode += count + " " + myLine + "\n";

                if (myLine.trim().startsWith("//")) {
                    continue;
                } else {
                    codeWithoutSingleLineComment += count + " " + myLine + "\n";
                }
                count++;
            }

            String codeWithoutSingleAndMultiLineComment = getCodeWithoutMultilineComment(codeWithoutSingleLineComment);

            String[] lineArray = codeWithoutSingleAndMultiLineComment.split("\n");
            String intermidiateCOde = "";

            for (int i = 0; i < lineArray.length; i++) {
                String[] tokenArray = lineArray[i].split(" ");


                String myString = "";
                for (String aString : tokenArray) {
                    if (getCategory(aString).equals("id")) {
                        myString += "id " + aString + " ";
                    } else {
                        myString += aString + " ";
                    }
                }
                intermidiateCOde += myString.trim() + "\n";
            }

            intermidiateCOde = intermidiateCOde.replace(";", " ;");
            intermidiateCOde = intermidiateCOde.replace("{", " { ");
            intermidiateCOde = intermidiateCOde.replace("}", " }");
            intermidiateCOde = intermidiateCOde.replace("(", " ( ");
            intermidiateCOde = intermidiateCOde.replace(")", " ) ");
            intermidiateCOde = intermidiateCOde.replace("=", " = ");
            intermidiateCOde = intermidiateCOde.replace("<", " < ");
            intermidiateCOde = intermidiateCOde.replace("+", " + ");
            intermidiateCOde = intermidiateCOde.replace("*", " * ");
            //System.out.println(intermidiateCOde);
            lineArray = intermidiateCOde.split("\n");

            Stack<String> bracketStack = new Stack<>();

            for(int i=0;i<lineArray.length;i++){
                String[] arr = lineArray[i].split(" ");

                for(String aString: arr){
                    if(aString.equals("{")){
                        bracketStack.push((i+1)+" "+"{");
                    }
                    else if(aString.equals("}")){
                        if(bracketStack.isEmpty()){
                            System.out.println("Misplaced ‘}’ at line "+(i+1));
                        }
                        else{
                            bracketStack.pop();
                        }
                    }
                }

                while (!bracketStack.isEmpty()){
                    String[] myArr = bracketStack.pop().split(" ");
                    System.out.println("No closing '}' for "+myArr[0]+" line opening {");
                }

            }

                       Stack<String> ifElseStack = new Stack<>();

            for(int i=0;i<lineArray.length;i++){
                String[] arr = lineArray[i].split(" ");

                for(String aString: arr){
                    if(aString.equals("if")){
                        ifElseStack.push((i+1)+" "+"if");
                    }
                    else if(aString.equals("else")){
                        if(ifElseStack.isEmpty()){
                            System.out.println("Unmatched ‘else’ at line "+(i+1));
                        }
                        else{
                            ifElseStack.pop();
                        }
                    }
                }



            }


            Stack<String> bracesStack = new Stack<>();

            for(int i=0;i<lineArray.length;i++){
                String[] arr = lineArray[i].split(" ");

                for(String aString: arr){
                    if(aString.equals("(")){
                        bracesStack.push((i+1)+" "+"(");
                    }
                    else if(aString.equals(")")){
                        if(bracesStack.isEmpty()){
                            System.out.println("Misplaced ‘)’ at line "+(i+1));
                        }
                        else{
                            bracesStack.pop();
                        }
                    }
                }

                while (!bracesStack.isEmpty()){
                    String[] myArr = bracesStack.pop().split(" ");
                    System.out.println("No closing '(' for "+myArr[0]+" line opening (");
                }



            }



            fw.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lab3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lab3.class.getName()).log(Level.SEVERE, null, ex);
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

    public static boolean insert(List<MainA.SymbolTableData> aList, MainA.SymbolTableData adata) {
        return aList.add(adata);
    }

    public static MainA.SymbolTableData update(List<MainA.SymbolTableData> aList, MainA.SymbolTableData updatedData, int index) {
        MainA.SymbolTableData data = aList.get(index);
        data.indentifierName = updatedData.indentifierName;
        data.scope = updatedData.scope;
        data.identifierDataType = updatedData.identifierType;
        data.identifierType = updatedData.identifierType;

        return aList.set(index, data);
    }

    public static MainA.SymbolTableData delete(List<MainA.SymbolTableData> aList, int index) {
        return aList.remove(index);
    }

    public static int search(List<MainA.SymbolTableData> aList, MainA.SymbolTableData symbolTableData) {


        for (MainA.SymbolTableData aData : aList) {

            if (aData.identifierDataType.equals(symbolTableData.identifierDataType) && aData.indentifierName.equals(symbolTableData.indentifierName) && aData.scope.equals(symbolTableData.scope) && aData.identifierDataType.equals(symbolTableData.identifierDataType)) {
                return aData.id;
            }
        }

        return -1;
    }

    /**
     * Search by scope ,Name,
     */
    public static int search(List<MainA.SymbolTableData> aList, String scope, String name) {

        for (MainA.SymbolTableData aData : aList) {

            if (aData.indentifierName.equals(name) && aData.scope.equals(scope)) {
                return aData.id;
            }
        }

        return -1;
    }

    /**
     * Search by name ,idType,
     */
    public static int searchByNameAndIdType(List<MainA.SymbolTableData> aList, String name, String idType) {

        for (MainA.SymbolTableData aData : aList) {

            if (aData.identifierType.equals(idType) && aData.indentifierName.equals(name)) {
                return aData.id;
            }
        }

        return -1;
    }


    public static void display(List<MainA.SymbolTableData> aList) {

        for (MainA.SymbolTableData aData : aList) {
            System.out.format("%10s%10s%10s%10s%10s\n", aData.id, aData.indentifierName, aData.identifierType, aData.identifierDataType, aData.scope);
        }
    }

    public static boolean hasAnyDataType(int currentIndex, List<String> tokens) {
        if (currentIndex == 0) {
            return false;
        }

        for (int i = currentIndex - 1; i >= 0; i--) {
            if (tokens.get(i).equals("int") || tokens.get(i).equals("float") || tokens.get(i).equals("double") || tokens.get(i).equals("char") || tokens.get(i).equals("string") || tokens.get(i).equals("long")) {
                return true;
            }

            if (tokens.get(i).equals(";")) {
                break;
            }
        }

        return false;
    }

    public static String getDataType(int currentIndex, List<String> tokens) {
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (tokens.get(i).equals("int") || tokens.get(i).equals("float") || tokens.get(i).equals("double") || tokens.get(i).equals("char") || tokens.get(i).equals("string") || tokens.get(i).equals("long")) {
                return tokens.get(i);
            }

            if (tokens.get(i).equals(";")) {
                break;
            }
        }
        return "paini";
    }

    public static String getScopeName(int currentIndex, List<String> tokens) {
        if (currentIndex == 0) {
            return "global";
        }

        int i = 0;
        boolean isOpenCurlyFound = false;
        for (i = currentIndex - 1; i >= 0; i--) {
            if (tokens.get(i) == "{") {
                isOpenCurlyFound = true;
                break;
            }
        }

        if (!isOpenCurlyFound) {
            boolean isOpenPerFound = false;

            for (i = currentIndex - 1; i >= 0; i--) {

                if (tokens.get(i).length() == 1) {
                    if (tokens.get(i).equals("(")) {
                        isOpenPerFound = true;
                        String[] arr = tokens.get(i - 1).split(" ");
                        if (arr.length > 1) {
                            //System.out.println("length is "+arr.length);
                            return arr[1];
                        }

                    } else if (tokens.get(i).equals("}")) {
                        break;
                    }
                }
            }

            if (!isOpenPerFound) {
                return "global";
            }
        } else {


            for (i = i - 1; i >= 0; i--) {
                String[] arr = tokens.get(i).split(" ");
                if (arr.length == 2) {
                    return arr[1];
                }
            }
        }
        return "global";
    }

    public static boolean isFunction(int currentIndes, List<String> tokens) {
        if (tokens.get(currentIndes + 1).equals("(")) {
            return true;
        }
        return false;

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
