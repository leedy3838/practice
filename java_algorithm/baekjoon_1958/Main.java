package baekjoon_1958;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input1 = br.readLine().toCharArray();
        char[] input2 = br.readLine().toCharArray();
        char[] input3 = br.readLine().toCharArray();

        int len1 = input1.length;
        int len2 = input2.length;
        int len3 = input3.length;

        int[][][] dp = new int[len1 + 1][len2 + 1][len3 + 1];

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                for(int k = 1; k <= len3; k++){
                    //아래의 3개 조건 중 1개가 사라져도 무방
                    //서로 겹치는 부분만 확인하기 때문
                    if(input1[i-1] == input2[j-1] && input1[i-1] == input3[k-1] && input2[j-1] == input3[k-1])
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    else
                        dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                }
            }
        }

        System.out.println(dp[len1][len2][len3]);
    }
}
