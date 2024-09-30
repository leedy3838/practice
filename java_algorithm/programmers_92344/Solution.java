package programmers_92344;

import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int R = board.length;
        int C = board[0].length;
        
        int[][] prefixSum = new int[R + 1][C + 1]; // prefixSum 배열 초기화
        
        // 스킬 적용
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];
            
            if (type == 1) { // 공격
                prefixSum[r1][c1] -= degree;
                prefixSum[r1][c2 + 1] += degree;
                prefixSum[r2 + 1][c1] += degree;
                prefixSum[r2 + 1][c2 + 1] -= degree;
            } else { // 회복
                prefixSum[r1][c1] += degree;
                prefixSum[r1][c2 + 1] -= degree;
                prefixSum[r2 + 1][c1] -= degree;
                prefixSum[r2 + 1][c2 + 1] += degree;
            }
        }
        
        // 가로 방향 누적 합 계산
        for (int i = 0; i < R; i++) {
            for (int j = 1; j <= C; j++) {
                prefixSum[i][j] += prefixSum[i][j - 1];
            }
        }
        
        // 세로 방향 누적 합 계산
        for (int i = 0; i < C; i++) {
            for (int j = 1; j <= R; j++) {
                prefixSum[j][i] += prefixSum[j - 1][i];
            }
        }
        
        // 최종 내구도 업데이트
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] += prefixSum[i][j];
            }
        }
        
        // 파괴되지 않은 건물 개수 세기
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
