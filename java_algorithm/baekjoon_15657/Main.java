package baekjoon_15657;

import java.io.*;
import java.util.*;

public class Main {
    static int[] map;
    static int N, M;
    static int[] answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N];
        answer = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            map[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(map);

        dfs(0, 0);
    }

    static void dfs(int idx, int count){
        if(count == M){
            for(int i = 0; i<M; i++)
                System.out.print(answer[i]+" ");
            System.out.println();

            return;
        }

        for(int i = idx; i<N; i++){
            answer[count] = map[i];
            dfs(i,count+1);
        }
    }
}
