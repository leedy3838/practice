package baekjoon_15654;

import java.io.*;
import java.util.*;

public class Main{
    static int[] intArr;
    static int[] dfsArr;
    static boolean[] visited;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        intArr = new int[N];
        dfsArr = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            intArr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(intArr);

        dfs(0);
    }

    static void dfs(int cnt){
        if(cnt == M){
            for(int i = 0; i<M; i++)
                System.out.print(dfsArr[i]+" ");
            System.out.println();

            return;
        }

        for(int i = 0; i<N; i++){
            if(!visited[i]) {
                dfsArr[cnt] = intArr[i];
                visited[i] = true;
                dfs(cnt+1);
                visited[i] = false;
            }
        }
    }
}
