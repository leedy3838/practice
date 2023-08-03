package baekjoon_2629;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        boolean[][] dp = new boolean[N+1][40_001];

        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int w = Integer.parseInt(st.nextToken());

            sum += w;
            dp[i][w] = true;

            for(int j = sum; j > 0; j--){
                if(dp[i-1][j]){
                    dp[i][j] = true;

                    //원래 있던 연산 결과에서 새로 들어온 값을 빼거나 더하기
                    if(j + w <= 40_000)
                        dp[i][j + w] = true;
                    if(j - w > 0)
                        dp[i][j - w] = true;

                    //원래 있던 연산 결과를 새로 들어온 값에서 빼기
                    if(w - j > 0)
                        dp[i][w - j] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++){
            if(dp[N][Integer.parseInt(st.nextToken())])
                sb.append("Y ");
            else
                sb.append("N ");
        }

        System.out.println(sb);
    }
}
