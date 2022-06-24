package baekjoon_1238_dijkstra;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<List<Node>> list = new ArrayList<>();
        List<List<Node>> reverseList = new ArrayList<>();

        for(int i = 0; i<=N; i++){
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            list.get(A).add(new Node(B, val));
            reverseList.get(B).add(new Node(A, val));
        }

        int[] dist = dijkstra(list, X);
        int[] reverseDist = dijkstra(reverseList, X);

        int max = Integer.MIN_VALUE;

        for(int i = 1; i <= N; i++)
            max = Math.max(max, dist[i]+reverseDist[i]);

        System.out.println(max);
    }

    static int[] dijkstra(List<List<Node>> l, int X){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE - 100);
        dist[X] = 0;

        boolean[] visited = new boolean[N+1];
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(X, 0));

        while(!q.isEmpty()){
            //a 노드가 중간에 거쳐갈 노드
            Node a = q.poll();
            if(visited[a.location])
                continue;

            visited[a.location] = true;

            //nextNode가 노드 a를 거쳐 갈 노드
            for(int i = 0; i<l.get(a.location).size(); i++){
                Node nextNode = l.get(a.location).get(i);

                if(dist[nextNode.location] > dist[a.location] + nextNode.val){
                    dist[nextNode.location] = dist[a.location] + nextNode.val;
                    q.offer(new Node(nextNode.location, dist[nextNode.location]));
                }
            }
        }

        return dist;
    }
}
class Node implements Comparable<Node>{
    int location, val;

    Node(int location, int val){
        this.location = location;
        this.val = val;
    }

    @Override
    public int compareTo(Node a){
        return val - a.val;
    }
}