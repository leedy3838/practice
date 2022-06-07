package baekjoon_1912;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] intArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());

            if(i == 0)
                intArr[i] = num;

            else{
                intArr[i] = Math.max(num, intArr[i-1] + num);
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i<N; i++)
            max = Math.max(max, intArr[i]);

        System.out.println(max);
    }
}
