package edu.aueb.ds.doubleQueue;

import java.util.ArrayList;
import java.util.Scanner;

public class PostfixToInfix {
    static boolean contains(char exp, char[] character) {
        for (int count = 0; count < character.length; count++){
            if(character[count] == exp) return true;
        }
        return false;
    }
    static boolean isOperand(char x) {
        return (x >= '0' && x <= '9');
    }

    static String getInfix(String exp) {
        StringDoubleEndedQueueImpl listPostfix = new StringDoubleEndedQueueImpl();

        for (int i = 0; i < exp.length(); i++) {
            if (isOperand(exp.charAt(i))){
                listPostfix.addLast(exp.charAt(i) + "");//push
            }
            else{
                String op1 = (String) listPostfix.removeLast();
                String op2 = (String) listPostfix.removeLast();
                listPostfix.addLast("(" + op2 + exp.charAt(i) + op1 + ")");
            }
        }
        return (String) listPostfix.getLast();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String postfix = scanner.nextLine();//try "65+84/*3-"
        char[] characters = {'1','2','3','4','5','6','7','8','9','0','+','-','*','/'};
        for (int i = 0; i < postfix.length(); i++){
            if (!contains(postfix.charAt(i), characters)) {
                System.err.println("Wrong input!");
                System.exit(0);
            }
        }
        String infix = getInfix(postfix);
        System.out.println(infix);

    }
}

