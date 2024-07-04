package baekjoon_15989;

import java.io.*;
import java.util.*;

public class Main {

    private static final List<Integer> data = new ArrayList<>();
    private static int maxVal = 0;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int num = Integer.parseInt(br.readLine());

            maxVal = Math.max(maxVal, num);
            data.add(num);
        }
    }

    private static void solveProblem() {
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[maxVal + 1];
        Arrays.fill(dp, 1);

        for (int i = 2; i <= maxVal; i++) {
            dp[i] += dp[i - 2];
        }

        for (int i = 3; i <= maxVal; i++) {
            dp[i] += dp[i - 3];
        }

        for (int num : data) {
            sb.append(dp[num]).append("\n");
        }

        System.out.println(sb);
    }
}
