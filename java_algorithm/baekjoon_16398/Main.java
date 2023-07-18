package baekjoon_16398;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] cost;
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        cost = new int[N][N];
        isVisit = new boolean[N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++)
                cost[i][j] = Integer.parseInt(st.nextToken());
        }

        //prim 알고리즘 -> 0번이 시작 정점
        Queue<Node> q = new PriorityQueue<>();
        isVisit[0] = true;
        for(int i = 1; i<N; i++)
            q.offer(new Node(0, i, cost[0][i]));

        //연결된 정점의 수
        int cnt = 1;
        //관리비용의 합
        long sum = 0;

        while(!q.isEmpty()){
            Node v = q.poll();

            if(isVisit[v.to])
                continue;

            isVisit[v.to] = true;
            for(int i = 0; i<N; i++)
                q.offer(new Node(v.to, i, cost[v.to][i]));

            cnt++;
            sum += v.val;

            if(cnt == N)
                break;
        }

        System.out.println(sum);
    }

    static class Node implements Comparable<Node>{
        int from, to, val;

        public Node(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }

        @Override
        public int compareTo(Node a){
            return this.val - a.val;
        }
    }
}
