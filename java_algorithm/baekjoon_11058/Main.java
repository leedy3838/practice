package baekjoon_11058;

import java.io.*;

public class Main {

    private static int N;
    private static final long[] dp = new long[101];
    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            for(int i = 1; i <= 3; i++)
                dp[i] = i;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        for(int i=1; i< N + 1; i++) {
            dp[i] = dp[i - 1] + 1;

            // 버튼 3회를 누르면 값이 dp[i-3]의 2배로 복사 -> 4회를 누르면 3배, 5회 4배, ..., j회 누르면 j-1배 복사
            // 점화식 : dp[i] = dp[i-j]*(j-1)
            if(i > 6) {
                for(int j = 2; j < 5; j++) {
                    dp[i] = Math.max(dp[i], dp[i - (j + 1)] * j);
                }
            }
        }

        System.out.println(dp[N]);
    }
}
