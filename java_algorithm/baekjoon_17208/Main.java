package baekjoon_17208;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //주문의 수
        int N = Integer.parseInt(st.nextToken());
        //치즈버거의 수
        int M = Integer.parseInt(st.nextToken());
        //감자튀김의 수
        int K = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[N+1][M+1][K+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            //치즈버거의 수
            int x = Integer.parseInt(st.nextToken());
            //감자튀김의 수
            int y = Integer.parseInt(st.nextToken());

            for(int j = 1; j <= M; j++){
                for(int k = 1; k <= K; k++){
                    if(x > j || y > k)
                        dp[i][j][k] = dp[i-1][j][k];

                    else
                        dp[i][j][k] = Math.max(dp[i-1][j-x][k-y] + 1, dp[i-1][j][k]);
                }
            }
        }

        System.out.println(dp[N][M][K]);
    }
}
