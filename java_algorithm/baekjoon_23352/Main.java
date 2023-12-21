package baekjoon_23352;

import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dR = {0, 0, 1, -1};
    private static final int[] dC = {1, -1, 0, 0};

    private static int N, M, maxLen = 0, maxVal = 0;
    private static int[][] map;

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

            map = new int[N][M];
            for(int i = 0; i < N; i++)
                map[i] = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0)
                    continue;

                bfs(i, j);
            }
        }

        System.out.println(maxVal);
    }

    private static void bfs(int row, int col) {

        boolean[][] isVisit = new boolean[N][M];
        Queue<Node> q = new LinkedList<>();

        int lastLen = -1, lastVal = 0;

        q.offer(new Node(row, col, 0));
        isVisit[row][col] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(node.len > lastLen) {
                lastVal = map[node.row][node.col];
                lastLen = node.len;
            } else {
                if(lastVal < map[node.row][node.col]) {
                    lastVal = map[node.row][node.col];
                    lastLen = node.len;
                }
            }

            for(int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if(dr < 0 || dc < 0 || dr >= N || dc >= M)
                    continue;
                if(map[dr][dc] == 0)
                    continue;
                if(isVisit[dr][dc])
                    continue;

                q.offer(new Node(dr, dc, node.len + 1));
                isVisit[dr][dc] = true;
            }
        }

        if(lastLen > maxLen) {
            maxVal = map[row][col] + lastVal;
            maxLen = lastLen;
        } else if(lastLen == maxLen) {
            maxVal = Math.max(maxVal, map[row][col] + lastVal);
        }
    }

    private static class Node {
        int row, col, len;

        public Node(int row, int col, int len) {
            this.row = row;
            this.col = col;
            this.len = len;
        }
    }
}
