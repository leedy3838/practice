package baekjoon_1106;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C+101];
        Arrays.fill(dp, 1_000_000_000);
        dp[0] = 0;

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int money = Integer.parseInt(st.nextToken());
            int personNum = Integer.parseInt(st.nextToken());

            for(int j = personNum; j<dp.length; j++){
                dp[j] = Math.min(dp[j], dp[j-personNum] + money);
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = C; i<dp.length; i++) {
            if(dp[i] != 0)
                ans = Math.min(ans, dp[i]);
        }

        System.out.println(ans);
    }
}