package baekjoon_15558;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, k;
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new boolean[2][N];

        for (int i = 0; i < 2; i++) {
            String input = br.readLine();

            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) == '1';
            }
        }
    }

    private static void solveProblem() {
        boolean isOk = false;

        boolean[][] isVisit = new boolean[2][N + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));


        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.col + 1 >= N || node.col + k >= N) {
                isOk = true;
                break;
            }


            if (!isVisit[node.row][node.col + 1] && map[node.row][node.col + 1]) {
                q.offer(new Node(node.row, node.col + 1, node.time + 1));
                isVisit[node.row][node.col + 1] = true;
            }
            if (!isVisit[Math.abs(node.row - 1)][node.col + k] && map[Math.abs(node.row - 1)][node.col + k]) {
                q.offer(new Node(Math.abs(node.row - 1), node.col + k, node.time + 1));
                isVisit[Math.abs(node.row - 1)][node.col + k] = true;
            }
            if (node.col - 1 > node.time && !isVisit[node.row][node.col - 1] && map[node.row][node.col - 1]) {
                q.offer(new Node(node.row, node.col - 1, node.time + 1));
                isVisit[node.row][node.col - 1] = true;
            }
        }

        if (isOk) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static class Node {
        int row, col, time;

        public Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
