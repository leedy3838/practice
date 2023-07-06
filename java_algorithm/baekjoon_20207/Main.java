package baekjoon_20207;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int MAX_DATE = 365;
        int[] date = new int[MAX_DATE+1];

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for(int j = start; j<=end; j++)
                date[j]++;
        }

        int sum = 0; int max = 0;
        int start = 0; int end = 0;

        for(int i = 1; i<=MAX_DATE; i++){
            max = Math.max(max, date[i]);

            if(start == 0)
                start = i;

            if(date[i] != 0)
                end = i;

            if(date[i] == 0){
                sum += (end-start+1)*max;

                max = 0;
                start = 0;
                end = 0;
            }
        }

        if(max != 0)
            sum += (end-start+1)*max;

        System.out.println(sum);
    }
}