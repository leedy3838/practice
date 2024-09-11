package programmers_올바른_괄호;

import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Integer> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '('){
                stack.add(0);
            }
            else {
                if (stack.isEmpty()) {
                    answer = false;
                    break;
                } else {
                    stack.pop();
                }
            }
        }
        
        if (!stack.isEmpty()) {
                answer = false;
        }

        return answer;
    }
}