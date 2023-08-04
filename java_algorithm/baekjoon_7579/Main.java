package baekjoon_7579;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //0번 index에는 바이트, 1번 index에는 비용
        int[][] input = new int[N+1][2];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            input[i][0] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            input[i][1] = Integer.parseInt(st.nextToken());

        //2차원에는 비용이 들어가고 value가 메모리의 크기
        //2처원에 매모리가 들어가면 크기가 안됨
        int[][] dp = new int[N+1][10_001];

        int costSum = 0;
        for(int i = 1; i <= N; i++){
            int nowMemory = input[i][0];
            int nowCost = input[i][1];

            dp[i][nowCost] = Math.max(dp[i][nowCost], nowMemory);

            costSum += nowCost;

            for(int j = 0; j <= costSum; j++){

                if(j >= nowCost)
                    dp[i][j] = Math.max(dp[i - 1][j - nowCost] + nowMemory, dp[i - 1][j]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }

        int minCost = Integer.MAX_VALUE;
        for(int i = 0; i <= costSum; i++){
            if(dp[N][i] >= M) {
                minCost = i;
                break;
            }
        }

        System.out.println(minCost);
    }
}