package programmers_77485;

import java.util.*;

class Solution {
    private int[][] map;
    
    private final List<Integer> answer = new ArrayList<>();
    
    public int[] solution(int rows, int columns, int[][] queries) {
        setting(rows, columns);
        solve(rows, columns, queries);
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private void setting(int rows, int columns) {
        map = new int[rows + 1][columns + 1];
        
        int count = 1;
        
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = count++;
            }
        }
    }
    
    private void solve(int rows, int columns, int[][] queries) {
        for (int[] query : queries) {
            int minV = Integer.MAX_VALUE;
            
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];
            
            int tmp = map[r1][c1];
            for (int i = r1; i < r2; i++) {
                map[i][c1] = map[i + 1][c1];
                minV = Math.min(minV, map[i][c1]);
            }
            
            for (int i = c1; i < c2; i++) {
                map[r2][i] = map[r2][i + 1];
                minV = Math.min(minV, map[r2][i]);
            }
            
            for (int i = r2; i > r1; i--) {
                map[i][c2] = map[i - 1][c2];
                minV = Math.min(minV, map[i][c2]);
            }
            
            for (int i = c2; i > c1; i--) {
                map[r1][i] = map[r1][i - 1];
                minV = Math.min(minV, map[r1][i]);
            }
            
            map[r1][c1 + 1] = tmp;
            
            answer.add(Math.min(minV, tmp));
        }
    }
}