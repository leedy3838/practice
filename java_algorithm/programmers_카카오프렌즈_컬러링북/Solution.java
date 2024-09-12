package programmers_카카오프렌즈_컬러링북;

import java.util.*;

class Solution {
    private int m, n;
    
    private int[][] picture;
    private boolean[][] isVisit;
    
    private final int[] dR = new int[]{0, 0, 1, -1};
    private final int[] dC = new int[]{1, -1, 0, 0};
    
    public int[] solution(int m, int n, int[][] picture) {
        int num = 0;
        int maxSize = 0;
        
        this.picture = picture;
        isVisit = new boolean[m][n];
        
        this.m = m;
        this.n = n;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0)
                    continue;
                if (isVisit[i][j])
                    continue;
                
                num++;
                maxSize = Math.max(maxSize, bfs(i, j));
            }
        }

        int[] answer = new int[2];
        answer[0] = num;
        answer[1] = maxSize;
        return answer;
    }
    
    private int bfs(int row, int col) {
        int cnt = 0;
        
        int color = picture[row][col];
        
        Queue<Node> q = new LinkedList<>();
        
        q.offer(new Node(row, col));
        isVisit[row][col] = true;
        cnt++;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];
                
                if (dr < 0 || dc < 0 || dr >= m || dc >= n)
                    continue;
                if (isVisit[dr][dc])
                    continue;
                if (picture[dr][dc] != color)
                    continue;
                
                q.offer(new Node(dr, dc));
                isVisit[dr][dc] = true;
                cnt++;
            }
        }
        
        return cnt;
    }
    
    private static class Node {
        int row, col;
        
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}