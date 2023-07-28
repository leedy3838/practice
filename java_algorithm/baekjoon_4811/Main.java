package baekjoon_4811;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[] dp = new long[31];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i<=30; i++){
            for(int j = 0; j<i; j++)
                dp[i] += dp[i-j-1]*dp[j];
        }

        int num = Integer.parseInt(br.readLine());
        while(num != 0){
            sb.append(dp[num]).append("\n");

            num = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
    }
}
