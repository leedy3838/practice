package baekjoon_14497;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int startR, startC, targetR, targetC;
    private static int[][] map;

    private static final int[] dR = new int[]{0, 0, 1, -1};
    private static final int[] dC = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken());
        startC = Integer.parseInt(st.nextToken());
        targetR = Integer.parseInt(st.nextToken());
        targetC = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String[] split = br.readLine().split("");

            for (int j = 1; j <= M; j++) {
                if (split[j - 1].equals("0")) {
                    map[i][j] = 0;
                } else if (split[j - 1].equals("1")) {  //벽을 -1로 표시
                    map[i][j] = 1;
                } else {    //시작, 끝
                    map[i][j] = 1;
                }
            }
        }
    }

    private static void solveProblem() {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startR, startC, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.row == targetR && node.col == targetC) {
                System.out.println(node.val);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = node.row + dR[i];
                int nextC = node.col + dC[i];

                if (nextR < 1 || nextC < 1 || nextR > N || nextC > M)
                    continue;
                if (map[nextR][nextC] == -1)    //이미 방문
                    continue;

                if (map[nextR][nextC] == 0) {           //빈 공간
                    map[nextR][nextC] = -1;
                    pq.add(new Node(nextR, nextC, node.val));
                } else if (map[nextR][nextC] == 1) {    //벽
                    map[nextR][nextC] = -1;
                    pq.add(new Node(nextR, nextC, node.val + 1));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int row, col, val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        public int compareTo(Node node) {
            return this.val - node.val;
        }
    }
}
