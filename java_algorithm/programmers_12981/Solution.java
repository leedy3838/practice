package programmers_12981;

import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        
        Set<String> s = new HashSet<>();
        String exStr = words[0];
        s.add(exStr);
        
        for (int i = 1; i < words.length; i++) {
            String str = words[i];
            
            if (s.contains(str) || exStr.charAt(exStr.length() - 1) != str.charAt(0)) {
                int person = i % n + 1;
                int turn = i / n;
                
                return new int[]{person, turn + 1};
            }
            
            s.add(str);
            exStr = str;
        }

        return new int[]{0, 0};
    }
}