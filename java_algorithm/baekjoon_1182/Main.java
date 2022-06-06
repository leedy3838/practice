package baekjoon_1182;

import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[] numArr;
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numArr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            numArr[i] = Integer.parseInt(st.nextToken());

        dfs();

        System.out.println(count);
    }

    static void dfs(){
        for(int i = 0; i<N; i++){
            visited[i] = true;
            dfs(numArr[i], 1, i);
            visited[i] = false;
        }
    }

    static void dfs(int sum, int cnt, int exIdx){
        if(sum == S)
            count++;

        if(cnt == N)
            return;

        for(int i = exIdx+1; i<N; i++){
            if(visited[i])
                continue;

            visited[i] = true;
            dfs(sum + numArr[i], cnt+1, i);
            visited[i] = false;
        }
    }
}
