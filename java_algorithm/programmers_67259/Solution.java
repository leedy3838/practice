package programmers_67259;

import java.util.*;

class Solution {
    private int N;
    private boolean[][][] isVisit;
    
    private final int[] dR = new int[]{0, 1, 0, -1};
    private final int[] dC = new int[]{-1, 0, 1, 0};

    public int solution(int[][] board) {
        N = board.length;
        isVisit = new boolean[N][N][4]; // 방향도 고려

        return bfs(board);
    }

    public int bfs(int[][] board) {
        int cost = Integer.MAX_VALUE;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, -1, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.row == N - 1 && node.col == N - 1) {
                cost = Math.min(cost, node.cost);
            }

            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if (dr < 0 || dc < 0 || dr >= N || dc >= N)
                    continue;
                if (board[dr][dc] == 1)
                    continue;

                int nCost = node.cost;
                if (node.dir == -1 || node.dir == i) {
                    nCost += 100;
                } else {
                    nCost += 600;
                }

                if (!isVisit[dr][dc][i] || board[dr][dc] >= nCost) { // 방문 안한 경우, 비용이 더 저렴한 경우
                    isVisit[dr][dc][i] = true;
                    board[dr][dc] = nCost;
                    q.add(new Node(dr, dc, i, nCost));
                }
            }
        }

        return cost;
    }

    static class Node {
        int row, col, dir, cost;

        Node(int row, int col, int dir, int cost) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cost = cost;
        }
    }
}