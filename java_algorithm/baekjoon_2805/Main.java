package baekjoon_2805;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] data = new int[N];
        long minVal = 0;
        long maxVal = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            data[i] = Integer.parseInt(st.nextToken());

            maxVal = Math.max(maxVal, data[i]);
        }

        while(minVal < maxVal){
            long middle = (minVal + maxVal)/2;

            long sum = 0;
            for(int i = 0; i<N; i++){
                if(data[i] > middle)
                    sum += data[i] - middle;
            }

            if(sum>=M)
                minVal = middle + 1;
            else
                maxVal = middle;
        }

        System.out.println(minVal-1);
    }
}
