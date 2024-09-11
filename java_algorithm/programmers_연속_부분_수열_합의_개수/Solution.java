package programmers_연속_부분_수열_합의_개수;

import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> s = new HashSet<>();
        
        int N = elements.length;
        
        for (int start = 0; start < N; start++) {
            int sum = 0;
            
            for (int size = 0; size < N; size++) {
                sum += elements[(start + size) % N];
                s.add(sum);
            }
        }
        
        return s.size();
    }
}