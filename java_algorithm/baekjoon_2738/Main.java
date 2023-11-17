package baekjoon_2738;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        int[][] A = new int[row][col];

        for(int i = 0; i < row; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < col; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < row; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < col; j++){
                A[i][j] += Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
