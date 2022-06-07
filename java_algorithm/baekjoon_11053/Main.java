package baekjoon_11053;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] intArr = new int[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            intArr[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++){
            dp[i] = 1;

            for(int j = 0; j<i; j++){
                if(intArr[i]>intArr[j] && dp[i] < dp[j]+1)
                    dp[i] = dp[j] + 1;
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i<N; i++)
            max = Math.max(max, dp[i]);

        System.out.println(max);
    }
}
