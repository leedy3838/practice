package programmers_단체사진_찍기;

import java.util.*;

class Solution {
    char[] character = {'A','C', 'F', 'J', 'M', 'N', 'R', 'T'};
    char[] dp = new char[8];
    boolean[] isUse = new boolean[8];
    String[] data;
    
    int answer = 0;
    
    public int solution(int n, String[] data) {
        
        this.data = data;
        
        permutation(0);
        
        return answer;
    }
    
    public void permutation(int idx) {
        if (idx == 8) {
            boolean isOk = true;
            
            for (String op : data) {
                char first = op.charAt(0);
                char second = op.charAt(2);
                char operation = op.charAt(3);
                int dist = op.charAt(4) - '0';
                
                int start = 0;
                while (dp[start] != first && dp[start] != second) {
                    start++;
                }
                
                int end = start + 1;
                while (dp[end] != first && dp[end] != second) {
                    end++;
                }
                
                int nowDist = end - start - 1;
                
                switch (operation) {
                    case '=':
                        if (nowDist != dist) {
                            isOk = false;
                        }
                        break;
                    case '<':
                        if (nowDist >= dist) {
                            isOk = false;
                        }
                        break;
                    case '>':
                        if (nowDist <= dist) {
                            isOk = false;
                        }
                        break;
                }
                
                if (!isOk)
                    break;
            }
            
            if (isOk)
                answer++;
            
            return;
        }
        
        for (int i = 0 ; i < 8; i++) {
            if (!isUse[i]) {
                dp[idx] = character[i];
                isUse[i] = true;
                permutation(idx + 1);
                isUse[i] = false;
            }
        }
    }
}