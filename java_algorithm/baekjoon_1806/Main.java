package baekjoon_1806;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] inputArr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            inputArr[i] = Integer.parseInt(st.nextToken());

        //two pointer 사용
        int sum = 0;
        int start = 0;
        int end = 0;

        int maxLen = Integer.MAX_VALUE;

        while(start <= N && end <= N){
            if(sum < S){
                sum += inputArr[end];
                end++;
            }
            else{
                maxLen = Math.min(maxLen, end - start);
                sum -= inputArr[start];
                start++;
            }
        }

        if(maxLen == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(maxLen);
    }
}
