package baekjoon_16120;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i<input.length(); i++){
            if(input.charAt(i)=='P')
                stack.push('P');
            else{
                if(stack.size()>=2 && i <input.length()-1){
                    stack.pop();
                    stack.pop();
                }
                else{
                    System.out.println("NP");
                    return;
                }
            }
        }

        if(stack.size() == 1)
            System.out.println("PPAP");
        else
            System.out.println("NP");
    }
}
