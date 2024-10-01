package programmers_154540;

import java.util.*;

class Solution {
    private int N, M;
    private boolean[][] isVisit;
    private String[] maps;
    
    private int[] dR = new int[]{0, 0, 1, -1};
    private int[] dC = new int[]{1, -1, 0, 0};
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        this.maps = maps;
        isVisit = new boolean[N][M];
        
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == 'X')
                    continue;
                if (isVisit[i][j])
                    continue;
                
                l.add(bfs(i, j));
            }
        }
        
        if (l.isEmpty()) {
            return new int[]{-1};
        } else {
            return l.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();
        }
    }
    
    private int bfs(int row, int col) {
        int val = 0;
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col));
        isVisit[row][col] = true;
        val += maps[row].charAt(col) - '0';
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];
                
                if (dr < 0 || dc < 0 || dr >= N || dc >= M)
                    continue;
                if (maps[dr].charAt(dc) == 'X')
                    continue;
                if (isVisit[dr][dc])
                    continue;
                
                q.offer(new Node (dr, dc));
                isVisit[dr][dc] = true;
                val += maps[dr].charAt(dc) - '0';
            }
        }
        
        return val;
    }
    
    static class Node {
        int row, col;
        
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}