package baekjoon_15655;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] data;
    static int[] answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        data = new int[N];
        answer = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            data[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(data);

        backTracking(0, 0);
    }

    static void backTracking(int idx, int count){
        if(count == M){
            for(int i = 0; i<M; i++)
                System.out.print(answer[i]+" ");
            System.out.println();
            return;
        }

        for(int i = idx; i<N; i++){
            answer[count] = data[i];
            backTracking(i+1, count+1);
        }
    }
}
