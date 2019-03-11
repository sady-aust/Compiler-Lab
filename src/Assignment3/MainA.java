package Assignment3;

import lab3.Lab3;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainA {
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
            FileReader fr = new FileReader(new File("F:\\CSE\\4.1\\CompilarLab\\src\\Assignment3\\input.txt"));
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(new File("F:\\CSE\\4.1\\CompilarLab\\src\\Assignment3\\output.txt"));

            String temp = "";
            String inputString = "";
            while ((temp = br.readLine()) != null) {
                inputString += temp + " ";
            }
            inputString = inputString.trim();
            inputString = inputString.replace("] ", "]  ");

            String[] inputStringTokens = inputString.split("  ");

            List<String> typeList = Arrays.asList(new String[]{"kw", "op", "par", "sep", "num", "unkn", "brc"});

            String formationForSymbolTable = "";

            for (String token : inputStringTokens) {

                for (String aType : typeList) {
                    if (token.contains(aType)) {
                        token = token.replace(aType, "");
                        token = token.replace(" ", "");
                    }

                }
                formationForSymbolTable += token;


            }
            System.out.println("After Step 1: ");
            System.out.println(formationForSymbolTable);

            fw.write(formationForSymbolTable);


            formationForSymbolTable = formationForSymbolTable.replace("][", "  ");
            String[] formationTableToken = formationForSymbolTable.split("  ");

            List<SymbolTableData> symbolTable = new ArrayList<>();

            for (int i = 0; i < formationTableToken.length; i++) {
                String aToken = formationTableToken[i];
                aToken = aToken.replace("[", "");
                aToken = aToken.replace("]", "");
                formationTableToken[i] = aToken;
            }

            HashMap<String, String> identifiersMap = new HashMap<>();
            for (int i = 0; i < formationTableToken.length; i++) {
                //removing [ ] character form tokens
                String aToken = formationTableToken[i];
                //System.out.println(aToken);

                String[] arr = aToken.trim().split(" ");

                if (arr.length == 2) {

                    if (arr[0].equals("id")) {
                        String name = arr[1];
                        String dataType = "";
                        String scope = "";
                        String identifyerType = "";
                        if (hasAnyDataType(i, Arrays.asList(formationTableToken))) {
                            dataType = getDataType(i, Arrays.asList(formationTableToken));


                            if (identifiersMap.containsKey(name)) {
                                if (!identifiersMap.get(name).equals("global")) {
                                    identifiersMap.put(name, getScopeName(i, Arrays.asList(formationTableToken)));
                                    scope = getScopeName(i, Arrays.asList(formationTableToken));
                                    // System.out.println( "1 "+name +" "+getScopeName(i,Arrays.asList(formationTableToken)));
                                }
                            } else {
                                identifiersMap.put(name, getScopeName(i, Arrays.asList(formationTableToken)));
                                scope = getScopeName(i, Arrays.asList(formationTableToken));
                                // System.out.println( "2 "+name +" "+getScopeName(i,Arrays.asList(formationTableToken)));
                            }


                            if (isFunction(i, Arrays.asList(formationTableToken))) {
                                identifyerType = "func";
                            } else {
                                identifyerType = "var";
                            }

                            insert(symbolTable, new SymbolTableData(symbolTable.size() + 1, name, identifyerType, dataType, scope));
                            //System.out.println(name+" "+identifyerType+" "+dataType+" "+scope);

                        }

                    }


                }
            }

            System.out.println("Symbol Table: ");
            display(symbolTable);

            //step 4
            String outputString = "";
           for (int i=0;i<formationTableToken.length;i++){
               String aToken = formationTableToken[i];



               String[] arr = aToken.trim().split(" ");

               if(arr.length ==2){

                   if (hasAnyDataType(i, Arrays.asList(formationTableToken))) {

                      String dataType = getDataType(i, Arrays.asList(formationTableToken));
                      String scope = getScopeName(i,Arrays.asList(formationTableToken));
                      String identifyerType ="";

                       if (isFunction(i, Arrays.asList(formationTableToken))) {
                           identifyerType = "func";
                       } else {
                           identifyerType = "var";
                       }


                       int id = search(symbolTable,new SymbolTableData(arr[1],identifyerType,dataType,scope));
                       outputString+="[id "+id+"]";

                   }
                   else{
                       String scope = getScopeName(i,Arrays.asList(formationTableToken));
                       String identifyerType="";
                       if (isFunction(i, Arrays.asList(formationTableToken))) {
                           identifyerType = "func";
                       } else {
                           identifyerType = "var";
                       }

                       int id = search(symbolTable,scope,arr[1]);


                       if(id ==-1){
                            id = searchByNameAndIdType(symbolTable,arr[1],identifyerType);


                       }

                       if(id ==-1){
                           outputString+="["+aToken+"]";
                       }
                       else{
                           outputString+="[id "+id+"]";
                       }

                   }

               }
               else{
                   outputString +="["+aToken+"]";
               }

           }

            System.out.println(outputString.trim());
            fw.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lab3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lab3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean insert(List<SymbolTableData> aList, SymbolTableData adata) {
        return aList.add(adata);
    }

    public static SymbolTableData update(List<SymbolTableData> aList, SymbolTableData updatedData, int index) {
        SymbolTableData data = aList.get(index);
        data.indentifierName = updatedData.indentifierName;
        data.scope = updatedData.scope;
        data.identifierDataType = updatedData.identifierType;
        data.identifierType = updatedData.identifierType;

        return aList.set(index, data);
    }

    public static SymbolTableData delete(List<SymbolTableData> aList, int index) {
        return aList.remove(index);
    }

    public static int search(List<SymbolTableData> aList,SymbolTableData symbolTableData) {


           for (SymbolTableData aData : aList) {

            if (aData.identifierDataType.equals(symbolTableData.identifierDataType) && aData.indentifierName.equals(symbolTableData.indentifierName) && aData.scope.equals(symbolTableData.scope) && aData.identifierDataType.equals(symbolTableData.identifierDataType)) {

                return aData.id;

            }
           }

        return -1;
    }

    /**
     *
     * Search by scope ,Name,
     */
    public static int search(List<SymbolTableData> aList,String scope,String name) {


        for (SymbolTableData aData : aList) {

            if (aData.indentifierName.equals(name) && aData.scope.equals(scope)) {

                return aData.id;

            }
        }

        return -1;
    }
    /**
     *
     * Search by name ,idType,
     */
    public static int searchByNameAndIdType(List<SymbolTableData> aList,String name,String idType) {


        for (SymbolTableData aData : aList) {

            if (aData.identifierType.equals(idType) && aData.indentifierName.equals(name)) {

                return aData.id;

            }
        }

        return -1;
    }




    public static void display(List<SymbolTableData> aList) {

        for (SymbolTableData aData : aList) {
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
                        if(arr.length>1){
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
}
