package baekjoon_1647;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Node>> ll = new ArrayList<>();
        boolean[] visited = new boolean[N+1];

        for(int i = 0; i<=N; i++)
            ll.add(new ArrayList<>());

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            ll.get(A).add(new Node(B, val));
            ll.get(B).add(new Node(A, val));
        }

        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));
        int sum = 0;
        int max = Integer.MIN_VALUE;

        while(!q.isEmpty()){
            Node node = q.poll();
            if(visited[node.B])
                continue;

            visited[node.B] = true;
            sum += node.val;
            max = Math.max(max, node.val);

            for(int i = 0; i<ll.get(node.B).size(); i++){
                Node nextNode = ll.get(node.B).get(i);

                if(visited[nextNode.B])
                    continue;

                q.offer(new Node(nextNode.B, nextNode.val));
            }
        }

        System.out.println(sum - max);
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