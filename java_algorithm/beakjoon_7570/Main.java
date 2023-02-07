package beakjoon_7570;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] dp = new int[N+1];

        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());

            dp[num] = dp[num-1]+1;
        }

        int max = 0;
        for(int i = 1; i<=N; i++)
            max = Math.max(max, dp[i]);

        System.out.println(N-max);
    }
}
