package baekjoon_1015;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] order = new int[N];
        boolean[] visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++){
            int min = Integer.MAX_VALUE;
            int minIdx = -1;

            for(int j = 0; j<N; j++){
                if(visited[j])
                    continue;

                if(min>arr[j]){
                    min = arr[j];
                    minIdx = j;
                }
            }

            order[minIdx] = i;
            visited[minIdx] = true;
        }

        for(int i = 0; i<N; i++)
            System.out.print(order[i] +" ");
        System.out.println();
    }
}