package baekjoon_1149;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] rgbArr = new int[N][3];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<3; j++)
                rgbArr[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i<N; i++){
            rgbArr[i][0] += Math.min(rgbArr[i-1][1], rgbArr[i-1][2]);
            rgbArr[i][1] += Math.min(rgbArr[i-1][0], rgbArr[i-1][2]);
            rgbArr[i][2] += Math.min(rgbArr[i-1][0], rgbArr[i-1][1]);
        }

        int min = Math.min(Math.min(rgbArr[N-1][0], rgbArr[N-1][1]), rgbArr[N-1][2]);

        System.out.println(min);
    }
} 
