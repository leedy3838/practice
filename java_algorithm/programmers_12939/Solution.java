package programmers_12939;

import java.util.*;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            
            max = Math.max(max, v);
            min = Math.min(min, v);
        }
        
        String answer = String.valueOf(min) + " " + String.valueOf(max);
        return answer;
    }
}