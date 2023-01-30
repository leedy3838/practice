package baekjoon_1240;

import java.io.*;
import java.util.*;

public class Main {
    static boolean visited[];
    static List<List<Node>> l;
    static StringBuilder sb;
    static boolean didIt = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        l = new ArrayList<>();
        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        for(int i = 1; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            l.get(A).add(new Node(B, val));
            l.get(B).add(new Node(A, val));
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            visited = new boolean[N+1];
            visited[start] = true;
            didIt = false;
            bfs(start, end, 0);
        }

        System.out.print(sb);
    }

    public static void bfs(int now, int target, int sum){
        if(didIt)
            return;

        int size = l.get(now).size();

        for(int i = 0; i < size; i++){
            if(l.get(now).get(i).vertex == target){
                sb.append(sum + l.get(now).get(i).val).append("\n");
                didIt = true;
                return;
            }
        }

        for(int i = 0; i < size; i++){
            Node node = l.get(now).get(i);

            if(visited[node.vertex])
                continue;

            visited[node.vertex] = true;
            bfs(node.vertex, target, sum + node.val);
        }
    }

    static class Node{
        int vertex, val;

        public Node(int vertex, int val) {
            this.vertex = vertex;
            this.val = val;
        }
    }
}