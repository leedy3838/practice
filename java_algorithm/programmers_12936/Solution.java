package programmers_12936;

import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];

        List<Integer> list = new ArrayList<>();
        long factorial = 1;
        int idx = 0;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
            list.add(i);
        }

        k--;

        while (n > 0) {
            factorial /= n;
            int val = (int) (k / factorial);
            answer[idx] = list.get(val);
            list.remove(val);
            
            k %= factorial;
            idx++;
            n--;
        }
        return answer;
    }
}
