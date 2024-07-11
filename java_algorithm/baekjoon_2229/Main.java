package baekjoon_2229;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] data;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        data = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solveProblem() {
        int[] dp = new int[N + 1];

        for (int numIdx = 1; numIdx <= N; numIdx++) {
            int minV = Integer.MAX_VALUE;
            int maxV = Integer.MIN_VALUE;

            for (int startIdx = numIdx; startIdx > 0; startIdx--) {
                minV = Math.min(minV, data[startIdx]);
                maxV = Math.max(maxV, data[startIdx]);

                dp[numIdx] = Math.max(dp[numIdx], dp[startIdx - 1] + maxV - minV);
            }
        }

        System.out.println(dp[N]);
    }
}
