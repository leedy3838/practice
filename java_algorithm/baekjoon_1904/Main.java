package baekjoon_1904;

import java.io.*;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1];

        if(N == 1){
            System.out.println(1);
            return;
        }

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i<=N; i++)
            dp[i] = -1;

        tile(N);

        System.out.println(dp[N]);
    }

    static int tile(int N){
        if(dp[N] == -1)
            dp[N] = (tile(N-1) + tile(N-2)) % 15746;
        return dp[N];
    }
}
