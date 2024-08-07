package baekjoon_1823;

import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        dp = new int[N + 1][N + 1];


        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solveProblem() {
        System.out.println(solve(1, N, 1));
    }

    static int solve(int left, int right, int day) {
        if (left > right) {
            return 0;
        }

        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        int leftUp = arr[left] * day + solve(left + 1, right, day + 1);
        int rightDown = arr[right] * day + solve(left, right - 1, day + 1);

        dp[left][right] = Math.max(leftUp, rightDown);

        return dp[left][right];
    }
}
