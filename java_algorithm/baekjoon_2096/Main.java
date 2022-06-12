package baekjoon_2096;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] maxArr = new int[3];
        int[] minArr = new int[3];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int third = Integer.parseInt(st.nextToken());

            if(i == 0){
                maxArr[0] = first;
                maxArr[1] = second;
                maxArr[2] = third;

                minArr[0] = first;
                minArr[1] = second;
                minArr[2] = third;
                continue;
            }

            //최대 점수를 구하는 배열
            int ex0 = maxArr[0], ex1 = maxArr[1], ex2 = maxArr[2];

            maxArr[0] = Math.max(ex0, ex1) + first;
            maxArr[1] = Math.max(Math.max(ex0, ex1), ex2) + second;
            maxArr[2] = Math.max(ex1, ex2) + third;

            //최소 점수를 구하는 배열
            int minEx0 = minArr[0], minEx1 = minArr[1], minEx2 = minArr[2];

            minArr[0] = Math.min(minEx0, minEx1) + first;
            minArr[1] = Math.min(Math.min(minEx0, minEx1), minEx2) + second;
            minArr[2] = Math.min(minEx1, minEx2) + third;
        }

        int max = Math.max(Math.max(maxArr[0], maxArr[1]), maxArr[2]);
        int min = Math.min(Math.min(minArr[0], minArr[1]), minArr[2]);

        System.out.println(max + " " + min);
    }
}
