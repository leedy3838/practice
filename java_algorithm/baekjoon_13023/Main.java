package baekjoon_13023;

import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> l = new ArrayList<>();
    static int N;
    static boolean[] visited;
    static boolean canDo = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++)
            l.add(new ArrayList<>());
        visited = new boolean[N];

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            l.get(B).add(A);
        }

        for(int i = 0; i<N; i++) {
            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;

            if(canDo)
                break;
        }

        if(!canDo)
            System.out.println(0);
    }

    static void dfs(int idx, int cnt){
        if(canDo)
            return;
        //만약 모든 정점을 거쳤으면 종료
        if(cnt == 5){
            System.out.println(1);
            canDo = true;
            return;
        }

        for(int i = 0; i<l.get(idx).size(); i++){
            if(canDo)
                return;

            int nextNum = l.get(idx).get(i);

            if(!visited[nextNum]){
                visited[nextNum] = true;
                dfs(nextNum, cnt+1);
                visited[nextNum] = false;
            }
        }
    }
}
