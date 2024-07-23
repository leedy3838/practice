package baekjoon_11985;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, M, K;
    private static int[] data;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //오렌지의 개수
        M = Integer.parseInt(st.nextToken());   //한 상자에 넣을 수 있는 오렌지 개수의 최대값
        K = Integer.parseInt(st.nextToken());   //상자를 포장하는 비용

        data = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solveProblem() {
        long[] dp = new long[N + 1];

        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            int maxV = Integer.MIN_VALUE;
            int minV = Integer.MAX_VALUE;

            for (int j = i; j >= 1; j--) {
                if (i - j + 1 > M)
                    break;

                maxV = Math.max(maxV, data[j]);
                minV = Math.min(minV, data[j]);

                dp[i] = Math.min(dp[i], K + (long) (i - j + 1) * (maxV - minV) + dp[j - 1]);
            }
        }

        System.out.println(dp[N]);
    }
}
