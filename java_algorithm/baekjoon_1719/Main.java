package baekjoon_1719;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<List<Node>> l = new ArrayList<>();
    static int[] firstGo;
    static int[] dist;
    static int[][] answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());
        answer = new int[N+1][N+1];

        while(M-->0){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            l.get(A).add(new Node(B, val));
            l.get(B).add(new Node(A, val));
        }

        for(int i = 1; i<=N; i++)
            dijkstra(i);

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++) {
                if(i == j)
                    System.out.print("- ");
                else
                    System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void dijkstra(int vertex){
        firstGo = new int[N+1];
        for(int i = 1; i<=N; i++) {
            if(i == vertex)
                firstGo[i] = 0;
            else
                firstGo[i] = i;
        }

        dist = new int[N+1];
        for(int i = 1; i<=N; i++){
            if(i == vertex)
                dist[i] = 0;
            else
                dist[i] = Integer.MAX_VALUE;
        }

        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(vertex, 0));

        while(!q.isEmpty()){
            Node a = q.poll();

            for(int i = 0; i<l.get(a.vertex).size(); i++){
                Node nextV = l.get(a.vertex).get(i);

                if(dist[nextV.vertex] > dist[a.vertex] + nextV.value){
                    dist[nextV.vertex] = dist[a.vertex] + nextV.value;
                    q.offer(new Node(nextV.vertex, dist[nextV.vertex]));

                    //처음 while문을 반복할 때를 제거
                    if(a.vertex == vertex)
                        continue;

                    firstGo[nextV.vertex] = firstGo[a.vertex];
                }
            }
        }

        for(int i = 1; i<=N; i++)
            answer[vertex][i] = firstGo[i];
    }
}
class Node implements Comparable<Node>{
    int vertex, value;

    Node(int vertex, int value){
        this.vertex = vertex;
        this.value = value;
    }

    @Override
    public int compareTo(Node a){
        return this.value - a.value;
    }
}