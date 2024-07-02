package baekjoon_20002;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];    //누적합

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = map[i - 1][j] + map[i][j -1] - map[i - 1][j - 1] + num;
            }
        }
    }

    private static void solveProblem() {
        int maxVal = Integer.MIN_VALUE;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                int size = 1;

                while (row - size >= 0 && col - size >= 0) {
                    int sum = map[row][col] - map[row - size][col] - map[row][col - size] + map[row - size][col - size];
                    maxVal = Math.max(maxVal, sum);

                    size++;
                }
            }
        }

        System.out.println(maxVal);
    }
}
