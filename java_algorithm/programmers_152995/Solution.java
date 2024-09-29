package programmers_152995;

import java.util.*;

class Solution {
    int[][] scores;
    public int solution(int[][] scores) {
        this.scores = scores;
        
        int answer = 1;
        
        int sum = scores[0][0] + scores[0][1];
        for (int[] score : scores) {
            if (sum < score[0] + score[1]) {
                if (check(score)) {
                    answer++;
                }
            }
        }
        
        for (int[] score : scores) {
            if (scores[0][0] < score[0] && scores[0][1] < score[1])
                return -1;
        }
        
        return answer;
    }
    
    private boolean check(int[] checkArr) {
        for (int[] score : scores) {
            if (score[0] > checkArr[0] && score[1] > checkArr[1])
                return false;
        }
        
        return true;
    }
}