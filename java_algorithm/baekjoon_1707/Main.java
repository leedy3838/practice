package baekjoon_1707;

import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> l;
    static boolean[] visited;
    static int[] type;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            visited = new boolean[V+1];
            type = new int[V+1];

            l = new ArrayList<>();
            for(int i = 0; i<=V; i++)
                l.add(new ArrayList<>());

            //그래프의 간선을 입력
            for(int i = 0; i<E; i++){
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                l.get(A).add(B);
                l.get(B).add(A);
            }

            flag = true;
            //연결 그래프가 아닐 수 있으므로 모든 정점에 대해서 dfs
            for(int i = 1; i<=V; i++)
                if(!visited[i])
                    dfs(i, 2);

            if(flag)
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int vertex, int exType){
        visited[vertex] = true;
        type[vertex] = exType == 1 ? 2 : 1;

        for(int i = 0; i<l.get(vertex).size(); i++){
            int nextVertex = l.get(vertex).get(i);

            if(visited[nextVertex]) {
                if (type[vertex] == type[nextVertex])
                    flag = false;
            }

            else
                dfs(nextVertex, type[vertex]);
        }
    }
}
