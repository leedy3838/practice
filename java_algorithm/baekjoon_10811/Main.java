package baekjoon_10811;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] array = new int[N+1];
        for(int i = 1; i <= N; i++)
            array[i] = i;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            while(A < B){
                int tmp = array[A];
                array[A] = array[B];
                array[B] = tmp;

                A++;
                B--;
            }
        }

        for(int i = 1; i <= N ; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
