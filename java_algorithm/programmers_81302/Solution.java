package programmers_81302;

import java.util.*;

class Solution {
    private final char[][] map = new char[5][5];
    
    private final int[] dR = {0, 0, 1, -1};
    private final int[] dC = {1, -1, 0, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i = 0; i < 5; i++) {
            String[] data = places[i];
            makeMap(data);
            answer[i] = findAnswer() ? 1 : 0;
        }
        
        return answer;
    }
    
    private void makeMap(String[] data) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = data[i].charAt(j);
            }
        }
    }
    
    private boolean findAnswer() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P') {
                    if (!bfs(i, j)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    private boolean bfs(int row, int col) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col, 0));
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            if (node.val == 2) {
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];
                
                if (dr < 0 || dc < 0 || dr >= 5 || dc >= 5)
                    continue;
                
                if (map[dr][dc] == 'P' && !(dr == row && dc == col)) {
                    return false;
                }
                
                if (map[dr][dc] == 'O') {
                    q.offer(new Node(dr, dc, node.val + 1));
                }
            }
        }
        
        return true;
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