package baekjoon_10942;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][N + 1];

        for (int startIdx = 1; startIdx <= N; startIdx++) {
            for (int endIdx = startIdx; endIdx <= N; endIdx++) {
                boolean isOk = true;

                int left = startIdx;
                int right = endIdx;

                while (left <= right) {

                    if (data[left] != data[right]) {
                        isOk = false;
                        break;
                    }

                    left++;
                    right--;
                }

                dp[startIdx][endIdx] = isOk;
            }
        }
    }

    private static void solveProblem() throws IOException {
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int startIdx = Integer.parseInt(st.nextToken());
            int endIdx = Integer.parseInt(st.nextToken());

            if (dp[startIdx][endIdx]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.print(sb);
    }
}
