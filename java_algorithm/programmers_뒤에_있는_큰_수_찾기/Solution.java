package programmers_뒤에_있는_큰_수_찾기;

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        Stack<Integer> s = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            int val = numbers[i];

            while (!s.isEmpty()) {
                int exVal = s.peek();

                if (exVal > val) {
                    answer[i] = exVal;
                    break;
                } else {
                    s.pop();
                }
            }

            s.push(val);
        }

        return answer;
    }
}