package programmers_169199;

import java.util.*;

class Solution {
    private int N, M, startR, startC;
    private char[][] board;
    
    private final int[] dR = new int[]{0, 0, 1, -1};
    private final int[] dC = new int[]{1, -1, 0, 0};
    
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        
        this.board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = board[i];
            
            for (int j = 0; j < M; j++) {
                this.board[i][j] = str.charAt(j);
                
                if (this.board[i][j] == 'R') {
                    startR = i;
                    startC = j;
                }
            }
        }
        
        return bfs();
    }
    
    private int bfs() {
        int answer = -1;
        
        boolean[][] isVisit = new boolean[N][M];
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startR, startC, 0));
        isVisit[startR][startC] = true;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int dr = node.row;
                int dc = node.col;
                
                while (true) {
                    if (dr + dR[i] < 0 || dr + dR[i] >= N)
                        break;
                    if (dc + dC[i] < 0 || dc + dC[i] >= M)
                        break;
                    if (board[dr + dR[i]][dc + dC[i]] == 'D')
                        break;
                    
                    dr += dR[i];
                    dc += dC[i];
                }
                
                if (isVisit[dr][dc])
                    continue;
                
                if (board[dr][dc] == 'G') {
                    return node.cnt + 1;
                } else {
                    q.offer(new Node(dr, dc, node.cnt + 1));
                    isVisit[dr][dc] = true;
                }
            }
        }
        
        return -1;
    }
    
    static class Node {
        int row, col, cnt;
        
        public Node(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
}