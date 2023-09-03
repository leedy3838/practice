package baekjoon_2166;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[][] pointArr = new long[N][2];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            pointArr[i][0] = Long.parseLong(st.nextToken());
            pointArr[i][1] = Long.parseLong(st.nextToken());
        }

        double sum = 0;
        for(int i = 1; i < N-1; i++)
            sum += calc(pointArr[0], pointArr[i], pointArr[i+1]) / 2;

        System.out.printf("%.1f\n", Math.abs(sum));
    }

    //신발끈 공식 이용
    static double calc(long[] first, long[] second, long[] third){

        double firstSum = first[0] * second[1] + second[0] * third[1] + third[0] * first[1];
        double secondSum = first[1] * second[0] + second[1] * third[0] + third[1] * first[0];

        return firstSum - secondSum;
    }
}
