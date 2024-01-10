package baekjoon_3055;

import java.io.*;
import java.util.*;

public class Main {

    private static int R, C;
    private static int startR, startC;
    private static char[][] map;
    private static int[] dR = {0, 0, 1, -1};
    private static int[] dC = {1, -1, 0, 0};

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new char[R][C];

            for (int i = 0; i < R; i++) {
                map[i] = br.readLine()
                        .replaceAll("\\s+", "")
                        .toCharArray();

                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 'S') {
                        startR = i;
                        startC = j;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int minTime = findMinTime(startR, startC);
        if (minTime != 0) {
            System.out.println(minTime);
        } else {
            System.out.println("KAKTUS");
        }
    }

    private static int findMinTime(int startR, int startC) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] isVisit = new boolean[R][C];

        q.offer(new Node(startR, startC, 0));
        isVisit[startR][startC] = true;

        int exTime = -1;
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (map[node.row][node.col] == 'D')
                return node.time;

            //시간이 지나면 물이 범람
            if (node.time != exTime) {
                waterFlood();
                exTime = node.time;
            }

            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if (dr < 0 || dc < 0 || dr >= R || dc >= C)
                    continue;
                if (isVisit[dr][dc])
                    continue;
                if (map[dr][dc] == '*' || map[dr][dc] == 'X')
                    continue;

                q.offer(new Node(dr, dc, node.time + 1));
                isVisit[dr][dc] = true;
            }
        }

        return 0;
    }

    private static void waterFlood() {

        boolean[][] isVisit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (isVisit[i][j])
                    continue;
                if(map[i][j] != '*')
                    continue;

                for (int k = 0; k < 4; k++) {
                    int dr = i + dR[k];
                    int dc = j + dC[k];

                    if (dr < 0 || dc < 0 || dr >= R || dc >= C)
                        continue;
                    if (map[dr][dc] == 'D' || map[dr][dc] == 'X' || map[dr][dc] == '*')
                        continue;

                    map[dr][dc] = '*';
                    isVisit[dr][dc] = true;
                }
            }
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
