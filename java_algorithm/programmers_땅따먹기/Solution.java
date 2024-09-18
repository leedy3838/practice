package programmers_땅따먹기;

import java.util.*;

class Solution {
    int solution(int[][] land) {
        int rowNum = land.length;
        
        int[][] dp = new int[rowNum][4];
        
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    dp[i][j] = land[i][j];
                } else {
                    int exMaxVal = 0;
                    for (int k = 0; k < 4; k++) {
                        if (k != j) {
                            exMaxVal = Math.max(exMaxVal, dp[i - 1][k]);
                        }
                    }
                    
                    dp[i][j] = exMaxVal + land[i][j];
                }
            }
        }

        int answer = 0;
        
        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp[rowNum - 1][i]);
        }
        
        return answer;
    }
}