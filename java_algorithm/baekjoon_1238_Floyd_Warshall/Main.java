package baekjoon_1238_Floyd_Warshall;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i == j)
                    map[i][j] = 0;
                else
                    map[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map[A][B] = val;
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                for(int k = 1; k<=N; k++){
                    if(map[j][i] == Integer.MAX_VALUE)
                        continue;
                    if(map[i][k] == Integer.MAX_VALUE)
                        continue;

                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }

        int maxTime = Integer.MIN_VALUE;

        for(int i = 1; i<=N; i++)
            maxTime = Math.max(maxTime, map[i][X]+map[X][i]);

        System.out.println(maxTime);
    }
}