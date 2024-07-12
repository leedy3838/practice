package baekjoon_3067;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();

    private static int target;
    private static List<Integer> coin;

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        int N = Integer.parseInt(br.readLine());

        coin = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coin.add(Integer.parseInt(st.nextToken()));
        }

        target = Integer.parseInt(br.readLine());
    }

    private static void solveProblem() throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            firstSetting();

            int[] dp = new int[target + 1];
            for (int coinV : coin) {
                dp[coinV] += 1;

                for (int i = coinV + 1; i <= target; i++) {
                    dp[i] += dp[i - coinV];
                }
            }

            sb.append(dp[target]).append("\n");
        }

        System.out.println(sb);
    }
}
