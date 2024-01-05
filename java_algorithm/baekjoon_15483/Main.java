package baekjoon_15483;

import java.io.*;

public class Main {

    private static String str1, str2;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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
        for(int i = 1; i <= str1Len; i++)
            dp[i][0] = i;
        for(int i = 1; i <= str2Len; i++)
            dp[0][i] = i;

        for (int i = 1; i <= str1Len; i++) {
            for (int j = 1; j <= str2Len; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
            }
        }

        System.out.println(dp[str1Len][str2Len]);
    }
}
