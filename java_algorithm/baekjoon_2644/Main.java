package baekjoon_2644;

import java.io.*;
import java.util.*;

public class Main {
    static int N, v2;
    static boolean[] visited;
    static List<List<Integer>> l = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];

        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        while(M-->0){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            l.get(B).add(A);
        }

        int answer = dfs(v1, 0);
        System.out.println(answer);
    }

    static int dfs(int v, int cnt){
        int answer = -1;

        if(v == v2)
            return cnt;

        for(int i = 0; i<l.get(v).size(); i++){
            int nextV = l.get(v).get(i);

            if(visited[nextV])
                continue;

            visited[nextV] = true;
            answer = dfs(nextV, cnt+1);
            visited[nextV] = false;

            if(answer != -1)
                break;
        }

        return answer;
    }
}
