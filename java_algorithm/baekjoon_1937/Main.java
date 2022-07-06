package baekjoon_1937;

import java.io.*;
import java.util.*;

public class Main {
    static int N, max = Integer.MIN_VALUE;
    static int[][] map;
    //dp의 각 칸에는 그 칸에서 시작해서 탐색했을 때 가장 많이 칸을
    //이동할 수 있을 때의 칸 수를 입력
    static int[][] dp;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        map = new int[N][N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        pandaLotate();

        System.out.println(max);
    }

    static void pandaLotate(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                max = Math.max(max, dfs(i, j));
            }
        }
    }

    static int dfs(int row, int col){
        if((dp[row][col] != 0))
            return dp[row][col];

        dp[row][col] = 1;

        int dr, dc;
        for(int i = 0; i<4; i++){
            dr = row + dR[i];
            dc = col + dC[i];

            if(dr<0||dc<0||dr>=N||dc>=N)
                continue;
            if(map[dr][dc] <= map[row][col])
                continue;

            dp[row][col] = Math.max(dp[row][col], dfs(dr, dc) + 1);
        }

        return dp[row][col];
    }
}