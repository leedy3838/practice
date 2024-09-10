package programmers_게임_맵_최단거리;

import java.util.*;

class Solution {
    private static final int[] dR = new int[]{0, 0, 1, -1};
    private static final int[] dC = new int[]{1, -1, 0, 0};

    public int solution(int[][] maps) {
        int answer = -1;

        int n = maps.length;
        int m = maps[0].length;

        boolean[][] isVisit = new boolean[n][m];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1));
        isVisit[0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.row == n - 1 && node.col == m - 1) {
                answer = node.val;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if (dr < 0 || dc < 0 || dr >= n || dc >= m)
                    continue;
                if (maps[dr][dc] == 0)
                    continue;
                if (isVisit[dr][dc])
                    continue;

                q.offer(new Node(dr, dc, node.val + 1));
                isVisit[dr][dc] = true;
            }
        }

        return answer;
    }

    private static class Node {
        int row, col, val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}