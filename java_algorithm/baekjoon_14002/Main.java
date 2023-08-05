package baekjoon_14002;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args ) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++)
            input[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N+1];
        int maxVal = -1;
        int maxIdx = -1;

        for(int i = 1; i<=N; i++) {
            dp[i] = 1;

            for(int j = 1; j < i; j++) {
                if(input[i] > input[j] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
            }

            if(maxVal < dp[i]) {
                maxVal = dp[i];
                maxIdx = i;
            }
        }

        int exVal = maxVal;
        int[] ans = new int[maxVal+1];

        for(int i = maxIdx; i > 0; i--){
            if(dp[i] == exVal){
                ans[exVal] = input[i];
                exVal--;
            }
        }

        sb.append(maxVal).append("\n");
        for(int i = 1; i<ans.length; i++)
            sb.append(ans[i]).append(" ");

        System.out.println(sb);
    }
}
