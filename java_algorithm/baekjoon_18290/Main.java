package baekjoon_18290;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, M, K, answer = Integer.MIN_VALUE;
    private static boolean[][] isVisit;
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

        N = Integer.parseInt(st.nextToken());   //row 수
        M = Integer.parseInt(st.nextToken());   //col 수
        K = Integer.parseInt(st.nextToken());   //찾을 데이터 수

        isVisit = new boolean[N][M];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solveProblem() {
        getMax(0, 0);

        System.out.println(answer);
    }

    private static void getMax(int numCount, int sum) {
        if (numCount == K) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isVisit[i][j]) {
                    if (check(i, j)) {
                        isVisit[i][j] = true;
                        getMax(numCount + 1, sum + map[i][j]);
                        isVisit[i][j] = false;
                    }
                }
            }
        }
    }

    public static boolean check(int row, int col) {
        boolean canVisit = true;

        for (int i = 0; i < 4; i++) {
            int dr = row + dR[i];
            int dc = col + dC[i];

            if (dr < 0 || dc < 0 || dr >= N || dc >= M)
                continue;

            if (isVisit[dr][dc]) {
                canVisit = false;
                break;
            }
        }
        return canVisit;
    }
}
