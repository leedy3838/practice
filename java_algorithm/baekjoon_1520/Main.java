package baekjoon_1520;

import java.io.*;
import java.util.*;

public class Main {

    private static int M, N;
    private static int[][] map;
    private static int[][] dp;

    private static final int[] dR = {0, 0, 1, -1};
    private static final int[] dC = {1, -1, 0, 0};

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting(){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            dp = new int[M][N];
            map = new int[M][];

            for(int i = 0; i < M; i++){
                map[i] = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                Arrays.fill(dp[i], -1);
            }
        } catch (IOException e){
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem(){

        int routeNum = dfs(0, 0);
        System.out.println(routeNum);
    }

    //현재 좌표에서 M-1, N-1까지 갈 수 있는 경우의 수 return
    private static int dfs(int row, int col){

        //마지막 칸
        if(row == M-1 && col == N-1){
            return 1;
        }

        dp[row][col] = 0;

        for(int i = 0; i < 4; i++){
            int dr = row + dR[i];
            int dc = col + dC[i];

            if(dr < 0 || dc < 0 || dr >= M || dc >= N)
                continue;
            //진행하려는 위치가 더 낮지 않은 경우
            if(map[dr][dc] >= map[row][col])
                continue;
            //이미 해당 경로에서 M-1, N-1까지 가는 경우의 수를 아는 경우
            if(dp[dr][dc] != -1){
                dp[row][col] += dp[dr][dc];
                continue;
            }

            dp[row][col] += dfs(dr, dc);
        }

        return dp[row][col];
    }
}
