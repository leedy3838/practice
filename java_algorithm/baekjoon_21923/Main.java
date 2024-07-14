package baekjoon_21923;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solveProblem() {
        int[][] upDp = new int[N + 2][M + 2];

        //상승 비행 - 시작점부터 시작
        for (int row = N; row > 0; row--) {
            for (int col = 1; col <= M; col++) {
                if (row == N && col == 1) {         //시작 지점
                    upDp[N][1] = map[N][1];
                } else if (row == N) {             //시작 행일 때
                    upDp[row][col] = upDp[row][col - 1] + map[row][col];
                } else if (col == 1) {              //시작 열일 때
                    upDp[row][col] = upDp[row + 1][col] + map[row][col];
                } else {
                    upDp[row][col] = Math.max(upDp[row][col - 1], upDp[row + 1][col]) + map[row][col];
                }
            }
        }

        int[][] downDp = new int[N + 2][M + 2];

        //하강 비행 - 도착점부터 시작
        for (int row = N; row > 0; row--) {
            for (int col = M; col > 0; col--) {
                if (row == N && col == M) {         //시작 지점
                    downDp[N][M] = map[N][M];
                } else if (row == N) {             //시작 행일 때
                    downDp[row][col] = downDp[row][col + 1] + map[row][col];
                } else if (col  == M) {             //시작 열일 때
                    downDp[row][col] = downDp[row + 1][col] + map[row][col];
                } else {
                    downDp[row][col] = Math.max(downDp[row][col + 1], downDp[row + 1][col])+ map[row][col];
                }
            }
        }

        int maxVal = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                maxVal = Math.max(maxVal, upDp[i][j] + downDp[i][j]);
            }
        }

        System.out.println(maxVal);
    }
}
