package baekjoon_24479;

import java.io.*;
import java.util.*;

public class Main {
    static int cnt = 1;
    static int[] answer;
    static boolean[] visited;
    static List<List<Integer>> l = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        answer = new int[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        while(M-->0){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            l.get(B).add(A);
        }

        for(int i = 1; i<=N; i++)
            Collections.sort(l.get(i));

        visited[R] = true;
        answer[R] = cnt++;
        dfs(R);

        for(int i = 1; i<=N; i++)
            System.out.println(answer[i]);
    }

    static void dfs(int vertex){
        for(int i = 0; i<l.get(vertex).size(); i++){
            int nextV = l.get(vertex).get(i);

            if(visited[nextV])
                continue;

            visited[nextV] = true;
            answer[nextV] = cnt++;
            dfs(nextV);
        }
    }
}
