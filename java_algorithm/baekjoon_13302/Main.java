package baekjoon_13302;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static boolean[] restDay;
    private static int[][] dp;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            restDay = new boolean[N + 1];
            dp = new int[N + 1][N + 1];

            if(M == 0)
                return;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int day = Integer.parseInt(st.nextToken());
                restDay[day] = true;
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {
        System.out.println(dfs(1, 0));
    }

    private static int dfs(int day, int coupon) {

        if (day > N)
            return 0;

        if (dp[day][coupon] != 0)
            return dp[day][coupon];

        dp[day][coupon] = Integer.MAX_VALUE;
        if (restDay[day]) { //쉬는 날인 경우
            dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 1, coupon));
        } else {            //쉬는 날이 아닌 경우
            if (coupon >= 3) {  //쿠폰이 3개 이상인 경우
                dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 1, coupon - 3));
            }

            dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 1, coupon) + 10000);
            dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 3, coupon + 1) +25000);
            dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 5, coupon + 2) + 37000);
        }

        return dp[day][coupon];
    }
}
