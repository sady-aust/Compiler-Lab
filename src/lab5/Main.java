package lab5;

import java.util.*;
import java.math.*;
import java.util.*;

public class Main {
    static class Node {
        String value;
        String left;
        String middle;
        String right;

        public Node(String value, String left, String middle, String ritght) {
            this.value = value;
            this.left = left;
            this.middle = middle;
            this.right = ritght;
        }

        public Node(String value) {
            this.value = value;
            this.left = null;
            this.middle = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();

        map.put("E", Arrays.asList(new String[]{"T", "E'"}));
        map.put("E'", Arrays.asList(new String[]{"+", "T", "E'", "| e"}));
        map.put("T", Arrays.asList(new String[]{"F", "T'"}));
        map.put("T'", Arrays.asList(new String[]{"*", "F", "T'", "| e"}));
        map.put("F", Arrays.asList(new String[]{"(E)", "| id"}));

        //id + id*id

        Node root = new Node("E");
        LinkedList<String> myQueue = new LinkedList<>();

        myQueue.push("E");
        while (!myQueue.isEmpty()){

        }


    }
}
