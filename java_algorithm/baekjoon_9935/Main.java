package baekjoon_9935;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String bomb = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.add(str.charAt(i));

            if (stack.size() >= bomb.length()) {
                boolean isSame = true;

                for (int j = 0; j < bomb.length(); j++) {
                    if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                        isSame = false;
                        break;
                    }
                }

                if (isSame) {
                    for (int j = 0; j < bomb.length(); j++)
                        stack.pop();
                }
            }
        }

        for (char ch : stack)
            sb.append(ch);

        System.out.println(stack.isEmpty() ? "FRULA" : sb.toString());
    }
}
