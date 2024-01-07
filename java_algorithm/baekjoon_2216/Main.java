package baekjoon_2216;

import java.io.*;
import java.util.*;

public class Main {

    private static int A, B, C;
    private static String str1, str2;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            str1 = br.readLine();
            str2 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int str1Len = str1.length();
        int str2Len = str2.length();

        int[][] dp = new int[str1Len + 1][str2Len + 1];
        //공백 처리
        for (int i = 1; i <= str1Len; i++)
            dp[i][0] = B * i;
        for (int i = 1; i <= str2Len; i++)
            dp[0][i] = B * i;

        for (int i = 1; i <= str1Len; i++) {
            for (int j = 1; j <= str2Len; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + A;
                else    //문자가 다른 경우 - max의 앞, 공백을 넣는 경우 - max의 뒤
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + C, Math.max(dp[i - 1][j] + B, dp[i][j - 1] + B));
            }
        }

        System.out.println(dp[str1Len][str2Len]);
    }
}
