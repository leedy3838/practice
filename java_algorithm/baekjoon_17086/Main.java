package baekjoon_17086;

import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dR = {0, 0, 1, 1, 1, -1, -1, -1};
    private static final int[] dC = {1, -1, -1, 0, 1, -1, 0, 1};
    private static int N, M;
    private static boolean[][] map;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        try {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new boolean[N][M];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < M; j++) {
                    if(Integer.parseInt(st.nextToken()) == 1)
                        map[i][j] = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int maxVal = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                maxVal = Math.max(maxVal, bfs(i, j));
            }
        }

        System.out.println(maxVal);
    }

    private static int bfs(int row, int col) {

        int[][] dist = new int[N][M];
        int maxVal = Integer.MIN_VALUE;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col));

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(map[node.row][node.col]){
                return dist[node.row][node.col];
            }

            for(int i = 0; i < 8; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if(dr < 0 || dc < 0 || dr >= N || dc >= M)
                    continue;
                if(dist[dr][dc] != 0)
                    continue;
                if(dr == row && dc == col)
                    continue;

                q.offer(new Node(dr, dc));
                dist[dr][dc] = dist[node.row][node.col] + 1;
                maxVal = Math.max(maxVal, dist[dr][dc]);
            }
        }

        return maxVal;
    }

    private static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
