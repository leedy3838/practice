package baekjoon_17218;

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

        for (int i = 1; i <= str1Len; i++) {
            for (int j = 1; j <= str2Len; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        printAnswer(dp);
    }

    private static void printAnswer(int[][] dp) {

        StringBuilder sb = new StringBuilder();

        int row = str1.length();
        int col = str2.length();
        int cnt = dp[row][col];

        while (cnt > 0) {
            if (dp[row - 1][col] == cnt)
                row--;
            else if (dp[row][col - 1] == cnt)
                col--;
            else {
                sb.append(str1.charAt(row - 1));
                cnt--;
                row--;
                col--;
            }
        }

        System.out.println(sb.reverse());
    }
}
