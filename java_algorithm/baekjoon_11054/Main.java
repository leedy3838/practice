package baekjoon_11054;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int[] increasingDp = new int[N+1];
        int[] decreasingDp = new int[N+1];

        for(int i = 1; i <= N; i++) {
            increasingDp[i] = 1;

            for(int j = 1; j < i; j++){
                if(input[i] > input[j] && increasingDp[i] < increasingDp[j] + 1) {
                    increasingDp[i] = increasingDp[j] + 1;
                }
            }
        }

        for(int i = N; i > 0; i--){
            decreasingDp[i] = 1;

            for(int j = N; j > i; j--){
                if(input[i] > input[j] && decreasingDp[i] < decreasingDp[j] + 1) {
                    decreasingDp[i] = decreasingDp[j] + 1;
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= N; i++)
            max = Math.max(max, increasingDp[i] + decreasingDp[i] - 1);

        System.out.println(max);
    }
}
