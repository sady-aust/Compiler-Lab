package Assignmnt4;

import java.util.*;
import java.math.*;
import java.io.*;

public class MachineCode {
    public static void main(String[] args) {
        try{


            FileReader fr = new FileReader(new File("F:\\CSE\\4.1\\CompilarLab\\src\\Assignmnt4\\treeAddressCode.txt"));
            BufferedReader br = new BufferedReader(fr);

            String s ="";
            int register = 0;
            String mov;
            String operation;
            String out;
            while ((s = br.readLine())!=null){
                String updatedString = "";
                s = s.replace("+"," + ");
                s = s.replace("-"," - ");
                s = s.replace("*"," * ");
                s = s.replace("/"," / ");
                s = s.replace("="," = ");

               String[] arr = s.split(" ");
                String operator = arr[3];
                switch (operator){
                    case "+":
                        mov = "MOV R"+Integer.toString(register)+","+arr[2];
                        operation ="ADD R"+Integer.toString(register)+", "+arr[4];

                        System.out.println("Satetment");
                         out = s.replace(" ","");
                        System.out.println(out);
                        System.out.println("Target Code");
                        System.out.println(mov);
                        System.out.println(operation);
                        break;
                    case "-":
                        mov = "MOV R"+Integer.toString(register)+","+arr[2];
                        operation="SUB R"+Integer.toString(register)+", "+arr[4];

                        System.out.println("Satetment");
                        out = s.replace(" ","");
                        System.out.println(out);
                        System.out.println("Target Code");
                        System.out.println(mov);
                        System.out.println(operation);
                        break;
                    case "*":
                        mov = "MOV R"+Integer.toString(register)+","+arr[2];
                        operation="MUL R"+Integer.toString(register)+", "+arr[4];

                        System.out.println("Satetment");
                        out = s.replace(" ","");
                        System.out.println(out);
                        System.out.println("Target Code");
                        System.out.println(mov);
                        System.out.println(operation);
                        break;
                    case "/":
                        mov = "MOV R"+Integer.toString(register)+","+arr[2];
                        operation="DIV R"+Integer.toString(register)+", "+arr[4];

                        System.out.println("Satetment");
                        out = s.replace(" ","");
                        System.out.println(out);
                        System.out.println("Target Code");
                        System.out.println(mov);
                        System.out.println(operation);
                        break;
                }

                System.out.println();
                register++;

            }

            fr.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
