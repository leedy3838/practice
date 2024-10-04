package programmers_132265;

import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int[] prefixSum = new int[topping.length];
        Set<Integer> s = new HashSet<>();
        
        prefixSum[0] = 1;
        s.add(topping[0]);
        
        for (int i = 1; i < topping.length; i++) {
            if (s.contains(topping[i])) {
                prefixSum[i] = prefixSum[i - 1];
            } else {
                s.add(topping[i]);
                prefixSum[i] = prefixSum[i - 1] + 1;
            }
        }
        
        int[] prefixSumBack = new int[topping.length];
        s.clear();
        
        prefixSumBack[topping.length - 1] = 1;
        s.add(topping[topping.length - 1]);
        
        for (int i = topping.length - 2; i >= 0; i--) {
            if (s.contains(topping[i])) {
                prefixSumBack[i] = prefixSumBack[i + 1];
            } else {
                s.add(topping[i]);
                prefixSumBack[i] = prefixSumBack[i + 1] + 1;
            }
        }
        
        int answer = 0;
        
        for (int i = 0; i < topping.length - 1; i++) {
            if (prefixSum[i] == prefixSumBack[i + 1]) {
                answer++;
            }
        }
        
        return answer;
    }
}