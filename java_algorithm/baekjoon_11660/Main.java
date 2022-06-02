package baekjoon_11660;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int num = Integer.parseInt(input[1]);

        int[][] intArr = new int[N][N];
        int[][] sumArr = new int[N][N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++){
                intArr[i][j] = Integer.parseInt(st.nextToken());

                if(i == 0 && j == 0) {
                    sumArr[i][j] += intArr[i][j];
                }
                else if(i == 0)
                    sumArr[i][j] = sumArr[i][j-1] + intArr[i][j];
                else if(j == 0)
                    sumArr[i][j] = sumArr[i-1][j] + intArr[i][j];
                else
                    sumArr[i][j] = sumArr[i][j-1] + sumArr[i-1][j] - sumArr[i-1][j-1] + intArr[i][j];
            }
        } 

        for(int i = 0; i<num; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;

            int sum = sumArr[y2][x2];
            if(x1 == 0 && y1 == 0);
            else if(x1 == 0)
                sum -= sumArr[y1-1][x2];
            else if(y1 == 0)
                sum -= sumArr[y2][x1-1];
            else{
                sum -= sumArr[y2][x1-1] + sumArr[y1-1][x2];
                sum += sumArr[y1-1][x1-1];
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
