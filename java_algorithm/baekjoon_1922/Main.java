package baekjoon_1922;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Node>> ll = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        for(int i = 0; i<N+1; i++)
            ll.add(new LinkedList<>());

        for(int i = 0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            ll.get(A).add(new Node(B, val));
            ll.get(B).add(new Node(A, val));
        }

        Queue<Node> q = new PriorityQueue<>();
        for(int i = 0; i<ll.get(1).size(); i++)
            q.offer(ll.get(1).get(i));
        visited[1] = true;
        int sum = 0;

        while(!q.isEmpty()){
            Node a = q.poll();

            if(visited[a.B])
                continue;

            visited[a.B] = true;
            sum += a.val;
            for(int i = 0; i<ll.get(a.B).size(); i++)
                q.offer(ll.get(a.B).get(i));
        }

        System.out.println(sum);
    }
}
class Node implements Comparable<Node>{
    int B, val;

    Node(int B, int val){
        this.B = B;
        this.val = val;
    }

    @Override
    public int compareTo(Node a){
        return val - a.val;
    }
}