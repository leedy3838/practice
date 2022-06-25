package baekjoon_1504;

import java.io.*;
import java.util.*;

public class Main {
    static List<List<Node>> l = new ArrayList<>();
    static int N, E;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        for(int i = 0; i<E; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            l.get(A).add(new Node(B, cost));
            l.get(B).add(new Node(A, cost));
        }

        //v1, v2를 입력 받아 두 정점을 거쳐 N으로 갈 때의 최단 경로의 거리 계산
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int toV1 = dijkstra(1, v1);
        int toV2 = dijkstra(1, v2);

        int v1Tov2 = dijkstra(v1, v2);

        int v2ToN = dijkstra(v2, N);
        int v1ToN = dijkstra(v1, N);

        boolean flagV1 = true;
        boolean flagV2 = true;

        if(toV1 == -1 || v1Tov2 == -1 || v2ToN == -1)
            flagV1 = false;
        if(toV2 == -1 || v1Tov2 == -1 || v1ToN == -1)
            flagV2 = false;

        int sumV1 = -1;
        int sumV2 = -1;

        if(flagV1)
            sumV1 = toV1 + v1Tov2 + v2ToN;
        if(flagV2)
            sumV2 = toV2 + v1Tov2 + v1ToN;

        printAnswer(sumV1, sumV2);
    }

    static int dijkstra(int start, int to){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        //PriorityQueue를 사용해서 chooose 메소드, 즉 시작 정점에 연결된 정점 중
        //가장 가중치가 작은 노드를 찾지 않아도 되도록 해줌
        Queue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        q.offer(new Node(start, 0));

        while(!q.isEmpty()){
            Node a = q.poll();

            if(visited[a.vertex])
                continue;

            visited[a.vertex] = true;
            //a 노드를 경유해서 l.get(a.vertex).get(i)에 있는 노드로 이동
            for(int i = 0; i<l.get(a.vertex).size(); i++){
                Node nextNode = l.get(a.vertex).get(i);

                if(dist[nextNode.vertex] > dist[a.vertex] + nextNode.cost){
                    dist[nextNode.vertex] = dist[a.vertex] + nextNode.cost;
                    q.offer(new Node(nextNode.vertex, dist[nextNode.vertex]));
                }
            }
        }

        if(dist[to] == Integer.MAX_VALUE)
            return -1;
        else
            return dist[to];
    }

    static void printAnswer(int v1, int v2){
        if(v1 == -1 && v2 == -1)
            System.out.println(-1);
        else if(v1 == -1)
            System.out.println(v2);
        else if(v2 == -1)
            System.out.println(v1);
        else
            System.out.println(Math.min(v1, v2));
    }
}
class Node implements Comparable<Node>{
    int vertex, cost;

    Node(int vertex, int cost){
        this.vertex = vertex;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node a){
        return this.cost - a.cost;
    }
}