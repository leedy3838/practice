package baekjoon_6087;

import java.io.*;
import java.util.*;

public class Main {

    private static int W, H, startR = -1, startC = -1, targetR = -1, targetC = -1;
    private static boolean[][] map;

    //우(0), 하(1), 좌(2), 상(3)
    private static final int[] dR = new int[]{0, 1, 0, -1};
    private static final int[] dC = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());   //너비(col)
        H = Integer.parseInt(st.nextToken());   //높이(row)

        map = new boolean[H][W];

        for (int row = 0; row < H; row++) {
            String input = br.readLine();

            for (int col = 0; col < W; col++) {
                char alpha = input.charAt(col);

                if (alpha == '.') {
                    map[row][col] = true;
                } else if (alpha == '*') {
                    map[row][col] = false;
                } else if (alpha == 'C') {
                    map[row][col] = true;

                    if (startR == -1 && startC == -1) {
                        startR = row;
                        startC = col;
                    } else {
                        targetR = row;
                        targetC = col;
                    }
                }
            }
        }
    }

    private static void solveProblem() {
        int answer = Integer.MAX_VALUE;

        int[][][] mirrorCnt = new int[4][H][W];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < H; j++) {
                Arrays.fill(mirrorCnt[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(startR, startC, 10, -1));

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.row == targetR && node.col == targetC) {
                answer = Math.min(answer, node.mirrorCnt);
                continue;
            }

            //시작 지점에서의 레이저
            for (int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if (dr < 0 || dc < 0 || dr >= H || dc >= W)
                    continue;
                if (!map[dr][dc])
                    continue;
                if (Math.abs(node.dir - i) == 2)    //정반대 방향
                    continue;

                int nextMirrorCnt = (node.dir == i) ? node.mirrorCnt : node.mirrorCnt + 1;

                if (mirrorCnt[i][dr][dc] > nextMirrorCnt) {
                    q.offer(new Node(dr, dc, i, nextMirrorCnt));
                    mirrorCnt[i][dr][dc] = nextMirrorCnt;
                }
            }
        }

        System.out.println(answer);
    }

    private static class Node implements Comparable<Node> {
        int row, col, dir, mirrorCnt;

        public Node(int row, int col, int dir, int mirrorCnt) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.mirrorCnt = mirrorCnt;
        }

        @Override
        public int compareTo(Node node) {
            return this.mirrorCnt - node.mirrorCnt;
        }
    }
}
