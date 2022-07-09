package baekjoon_2660;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<List<Integer>> l = new ArrayList<>();
    static int[] point;
    static int minPoint = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        point = new int[N+1];
        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(A == -1 && B == -1)
                break;

            l.get(A).add(B);
            l.get(B).add(A);
        }

        bfs();

        int cnt = 0;
        for(int i = 1; i<=N; i++){
            if(point[i] == minPoint)
                cnt++;
        }

        System.out.println(minPoint + " "+ cnt);
        for(int i = 1; i<=N; i++)
            if(point[i] == minPoint)
                System.out.print(i+" ");
        System.out.println();
    }

    static void bfs(){
        for(int i = 1; i<=N; i++){
            int val = bfs(i);

            point[i] = val;
            minPoint = Math.min(minPoint, val);
        }
    }

    static int bfs(int v){
        boolean[] visited = new boolean[N+1];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(v, 0));
        visited[v] = true;
        int cnt = 0;

        while(!q.isEmpty()){
            Node vertex = q.poll();

            for(int i = 0; i<l.get(vertex.v).size(); i++){
                int nextV = l.get(vertex.v).get(i);

                if(visited[nextV])
                    continue;

                visited[nextV] = true;
                q.offer(new Node(nextV, vertex.cnt+1));
            }

            cnt = vertex.cnt;
        }

        return cnt;
    }
}
class Node{
    int v, cnt;

    Node(int v, int cnt){
        this.v = v;
        this.cnt = cnt;
    }
}
