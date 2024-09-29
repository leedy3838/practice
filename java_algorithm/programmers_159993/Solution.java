package programmers_159993;

import java.util.*;

class Solution {
    private int startR, startC, tmpR, tmpC;
    private char[][] map;
    private int N, M;
    
    private final int[] dR = new int[]{0, 0, 1, -1};
    private final int[] dC = new int[]{1, -1, 0, 0};
    
    public int solution(String[] maps) {
        setting(maps);
        
        int first = bfs(startR, startC, false);
        if (first == 0) {
            return -1;
        }
        
        int second = bfs(tmpR, tmpC, true);
        if (second == 0) {
            return -1;
        }
        
        return first + second;
    }
    
    private void setting(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = maps[i].charAt(j);
                
                if (map[i][j] == 'S') {
                    startR = i;
                    startC = j;
                } else if (map[i][j] == 'L') {
                    tmpR = i;
                    tmpC = j;
                }
            }
        }
    }
    
    private int bfs(int row, int col, boolean isEnd) {
        int cnt = 0;
        
        boolean[][] isVisit = new boolean[N][M];
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col, 0));
        isVisit[row][col] = true;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];
                
                if (dr < 0 || dc < 0 || dr >= N || dc >= M)
                    continue;
                if (isVisit[dr][dc])
                    continue;
                if (map[dr][dc] == 'X')
                    continue;
                
                if (!isEnd) {
                    if (map[dr][dc] == 'L') {
                        return node.val + 1;
                    } else {
                        q.offer(new Node(dr, dc, node.val + 1));
                        isVisit[dr][dc] = true;
                    }
                } else {
                    if (map[dr][dc] == 'E') {
                        return node.val + 1;
                    } else {
                        q.offer(new Node(dr, dc, node.val + 1));
                        isVisit[dr][dc] = true;
                    }
                }
            }
        }
        
        return 0;
    }
    
    private class Node {
        int row, col, val;
        
        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}