package baekjoon_17276;

import java.io.*;
import java.util.*;

public class Main {

    private static final StringBuilder sb = new StringBuilder();

    static int n, d;
    static int[][] arr, copy;

    public static void main(String[] args) throws IOException {
        firstSetting();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            if (d < 0)
                d += 360;
            d /= 45;

            arr = new int[n][n];
            copy = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    copy[i][j] = arr[i][j];
                }
            }

            solveProblem();
        }

        System.out.println(sb);
    }

    private static void solveProblem() {
        while (d-- > 0) {
            rotateArr();
        }

        for (int[] ar : arr) {
            for (int a : ar)
                sb.append(a).append(" ");
            sb.append("\n");
        }
    }

    private static void rotateArr() {

        for (int i = 0; i < n; i++) {
            copy[i][n / 2] = arr[i][i];
            copy[i][i] = arr[n / 2][i];
            copy[n / 2][i] = arr[n - i - 1][i];
            copy[n - i - 1][i] = arr[n - i - 1][n / 2];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = copy[i][j];
            }
        }
    }
}



