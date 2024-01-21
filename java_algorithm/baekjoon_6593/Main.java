package baekjoon_6593;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int L, R, C;
    private static char[][][] map;
    private static int startL, startR, startC, targetL, targetR, targetC;
    private static final int[] dR = {0, 0, 0, 0, 1, -1};
    private static final int[] dC = {0, 0, 1, -1, 0, 0};
    private static final int[] dL = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static boolean isFirstSettingOk() {

        try {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                return false;
            }

            map = new char[L][R][C];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String input = br.readLine();

                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = input.charAt(k);

                        if (map[i][j][k] == 'S') {
                            startL = i;
                            startR = j;
                            startC = k;
                        } else if (map[i][j][k] == 'E') {
                            targetL = i;
                            targetR = j;
                            targetC = k;
                        }
                    }
                }
                br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return true;
    }

    private static void solveProblem() {


        final StringBuilder sb = new StringBuilder();

        while (isFirstSettingOk()) {
            Queue<Node> q  = new PriorityQueue<>();
            boolean[][][] isVisit = new boolean[L][R][C];
            boolean isClear = false;

            q.offer(new Node(startL, startR, startC, 0));
            isVisit[startL][startR][startC] = true;

            while (!q.isEmpty()) {
                Node node = q.poll();

                if (node.layer == targetL && node.row == targetR && node.col == targetC) {
                    isClear = true;
                    sb.append("Escaped in ").append(node.time).append(" minute(s).\n");
                    break;
                }

                for (int i = 0; i < 6; i++) {
                    int dl = node.layer + dL[i];
                    int dr = node.row + dR[i];
                    int dc = node.col + dC[i];

                    if (dl < 0 || dr < 0 || dc < 0 || dl >= L || dr >= R || dc >= C)
                        continue;
                    if (isVisit[dl][dr][dc])
                        continue;
                    if (map[dl][dr][dc] == '#')
                        continue;

                    q.offer(new Node(dl, dr, dc, node.time + 1));
                    isVisit[dl][dr][dc] = true;
                }
            }

            if (!isClear)
                sb.append("Trapped!\n");
        }

        System.out.println(sb);
    }

    private static class Node implements Comparable<Node>{
        int layer, row, col, time;

        public Node(int layer, int row, int col, int time) {
            this.layer = layer;
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo(Node node) {
            return this.time - node.time;
        }
    }
}
