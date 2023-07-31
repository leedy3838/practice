package baekjoon_15817;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] dp = new int[x+1];
        dp[0] = 1;

        while(N-->0){
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            for(int i = x; i>0; i--){
                int LL = 0;

                for(int j = 0; j<C; j++){
                    LL += L;

                    if(i - LL <0)
                        break;

                    if(dp[i-LL] != 0)
                        dp[i] += dp[i-LL];
                }
            }
        }

        System.out.println(dp[x]);
    }
}