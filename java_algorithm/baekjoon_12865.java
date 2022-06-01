import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K+1][N+1];
        int[] weight = new int[N+1];
        int[] value = new int[N+1];

        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        //i는 들 수 있는 무게, j는 물건의 idx
        for(int i = 0; i<=K; i++){
            for(int j = 0; j<=N; j++){
                if(j == 0 || i == 0)
                    dp[i][j] = 0;

                else if(weight[j]>i){
                    dp[i][j] = dp[i][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i][j-1], value[j] + dp[i-weight[j]][j-1]);
                }
            }
        }

        System.out.println(dp[K][N]);
    }
}