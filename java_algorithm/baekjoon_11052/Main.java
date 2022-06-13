package baekjoon_11052;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N+1];
        int[] dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++)
            data[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=i; j++){
                dp[i] = Math.max(dp[i], dp[i-j] + data[j]);
            }
        }

        System.out.println(dp[N]);
    }
}
