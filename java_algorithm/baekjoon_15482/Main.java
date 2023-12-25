package baekjoon_15482;

import java.io.*;

public class Main {

    private static String input1;
    private static String input2;
    private static int[][] dp;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            input1 = br.readLine();
            input2 = br.readLine();

            dp = new int[input1.length() + 1][input2.length() + 1];
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int str1Len = input1.length();
        int str2Len = input2.length();

        for(int i = 1; i <= str1Len; i++) {
            for(int j = 1; j <= str2Len; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                //문자열에서는 1번째 글자가 0번 idx이기 떄문에 -1을 해줌
                if(input1.charAt(i - 1) == input2.charAt(j - 1))
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
            }
        }

        System.out.println(dp[str1Len][str2Len]);
    }
}
