package baekjoon_1080;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cnt = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int[][] target = new int[N][M];

        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split("");

            for(int j = 0; j<M; j++)
                map[i][j] = Integer.parseInt(input[j]);
        }
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split("");

            for(int j = 0; j<M; j++)
                target[i][j] = Integer.parseInt(input[j]);
        }

        for(int i = 0; i<N-2; i++){
            for(int j = 0; j<M-2; j++){
                if(map[i][j] != target[i][j]){
                    change(i, j);
                }
            }
        }

        boolean okay = true;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++)
                if(map[i][j] != target[i][j]) {
                    okay = false;
                    break;
                }
            if(!okay)
                break;
        }

        if(okay)
            System.out.println(cnt);
        else
            System.out.println(-1);
    }

    static void change(int row, int col){
        int[] dR = {0, 1, 2, 0, 1, 2, 0, 1, 2};
        int[] dC = {0, 0, 0, 1, 1, 1, 2, 2, 2};

        cnt++;

        for(int i = 0; i<9; i++){
            int dr = row + dR[i];
            int dc = col + dC[i];

            if(dr>=N||dc>=M)
                return;

            boolean isZero = map[dr][dc] == 0;
            if(isZero)
                map[dr][dc] = 1;
            else
                map[dr][dc] = 0;
        }
    }
}
