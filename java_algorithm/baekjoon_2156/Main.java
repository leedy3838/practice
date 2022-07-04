package baekjoon_2156;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N+1];

        for(int i = 1; i<=N; i++)
            data[i] = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        dp[1] = data[1];

        if(N>=2)
            dp[2] = data[1] + data[2];

        for(int i = 3; i<=N; i++)
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-3]+data[i-1]+data[i], dp[i-2]+data[i]));

        System.out.println(dp[N]);
    }
}
