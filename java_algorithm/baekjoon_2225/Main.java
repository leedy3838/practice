package baekjoon_2225;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] intArr= new int[K][N+1];

        for(int i = 0; i<N+1; i++)
            intArr[0][i] = 1;
        for(int i = 0; i<K; i++)
            intArr[i][0] = 1;

        for(int i = 1; i<K; i++){
            for(int j = 1; j<N+1; j++) {
                intArr[i][j] = intArr[i][j - 1] + intArr[i - 1][j];
                intArr[i][j] %= 1000000000;
            }
        }

        System.out.println(intArr[K-1][N]);
    }
}
