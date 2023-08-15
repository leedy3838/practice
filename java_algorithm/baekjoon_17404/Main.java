package baekjoon_17404;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] valArr;

    public static void main(String[] args) throws IOException{

        makeDpArr();

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++)
            ans = Math.min(ans, calcVal(i));

        System.out.println(ans);
    }

    public static void makeDpArr() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        valArr = new int[N+1][3];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j++)
                valArr[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    public static int calcVal(int firstColor){
        int[][] dp = new int[N+1][3];

        for(int i = 0; i < 3; i++){
            if(i == firstColor)
                dp[1][i] = valArr[1][firstColor];
            else
                dp[1][i] = 1001;
        }

        for(int i = 2; i <= N; i++){
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + valArr[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + valArr[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + valArr[i][2];
        }

        int returnV = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            if(i != firstColor)
                returnV = Math.min(returnV, dp[N][i]);
        }

        return returnV;
    }
}
