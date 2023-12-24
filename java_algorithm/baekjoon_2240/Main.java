package baekjoon_2240;

import java.io.*;
import java.util.*;

public class Main {

    //자두가 어느 나무에서 떨어지는지
    //true이면 1번 나무, false이면 2번 나무
    private static boolean[] jadooData;
    //column은 시간, row는 자리를 최대 옮기는 횟수
    private static int[][] dp;

    private static int T, W;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            T = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            jadooData = new boolean[T + 1];
            dp = new int[W + 1][T + 1];

            for(int i = 1; i <= T; i++) {
                jadooData[i] = br.readLine().equals("1");
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        //바꾸지 않는 경우
        for(int i = 1; i <= T; i++) {
            if(jadooData[i])
                dp[0][i] = dp[0][i-1] + 1;
        }

        for(int w = 1; w <= W; w++) {
            for(int t = 1; t <= T; t++) {

                //1번 나무로 돌아옴
                if(w % 2 == 0) {
                    //자두가 떨어지는 위치와 내 위치가 동일
                    if(jadooData[t])
                        dp[w][t] = Math.max(dp[w - 1][t - 1], dp[w][t - 1] + 1);
                    //동일하지 않음
                    else
                        dp[w][t] = Math.max(dp[w - 1][t - 1] + 1, dp[w][t - 1]);
                }
                //2번 나무
                else {
                    if(!jadooData[t])
                        dp[w][t] = Math.max(dp[w - 1][t - 1], dp[w][t - 1] + 1);
                    else
                        dp[w][t] = Math.max(dp[w - 1][t - 1] + 1, dp[w][t - 1]);
                }
            }
        }

        System.out.println(dp[W][T]);
    }
}
