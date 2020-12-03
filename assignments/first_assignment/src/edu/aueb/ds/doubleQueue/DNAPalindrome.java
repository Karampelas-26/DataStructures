package edu.aueb.ds.doubleQueue;

import java.util.Scanner;

public class DNAPalindrome {

    static StringDoubleEndedQueueImpl isPalindrome(String strDNA){
        StringDoubleEndedQueueImpl palindromeDNA = new StringDoubleEndedQueueImpl();

        for(int i=0;i<strDNA.length();i++){
            char c = strDNA.charAt(i);
            if(strDNA.charAt(i) == 'T'){
                palindromeDNA.addFirst("A");
            }
            if(strDNA.charAt(i) == 'A'){
                palindromeDNA.addFirst("T");
            }
            if(strDNA.charAt(i) == 'C'){
                palindromeDNA.addFirst("G");
            }
            if(strDNA.charAt(i) == 'G'){
                palindromeDNA.addFirst("C");
            }
        }
        return palindromeDNA;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String strDNA = scanner.nextLine();
        char[] characters = {'A','T','G','C'};
        for (int i = 0; i < strDNA.length(); i++){
            if (!PostfixToInfix.contains(strDNA.charAt(i), characters)) {
                System.err.println("Wrong input!");
                System.exit(0);
            }
        }
        StringDoubleEndedQueueImpl str = isPalindrome(strDNA);
        String temp = "";
        while (!str.isEmpty()){
            temp += str.removeFirst();
        }
        if (strDNA.equals(temp)){
            System.out.println("DNA is palindrome!");
        }
        else {
            System.out.println("DNA is not palindrome!");
        }
    }
}
