package baekjoon_10282;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int computer = Integer.parseInt(st.nextToken());
            int dependency = Integer.parseInt(st.nextToken());
            int hacked = Integer.parseInt(st.nextToken());

            List<List<Node>> l = new ArrayList<>();
            for(int i = 0; i<=computer; i++)
                l.add(new ArrayList<>());

            boolean[] visited = new boolean[computer+1];
            int[] answer = new int[computer+1];

            while(dependency-->0){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                l.get(b).add(new Node(a, s));
            }

            Queue<Node> q = new PriorityQueue<>();
            q.offer(new Node(hacked, 0));

            while(!q.isEmpty()){
                Node a = q.poll();

                if(visited[a.vertex])
                    continue;

                visited[a.vertex] = true;
                answer[a.vertex] = a.time;

                for(int i = 0; i<l.get(a.vertex).size(); i++){
                    Node nextV = l.get(a.vertex).get(i);
                    q.offer(new Node(nextV.vertex, a.time+nextV.time));
                }
            }

            int infected = 0;
            int time = 0;
            for(int i = 1; i<=computer; i++){
                if(visited[i])
                    infected++;

                time = Math.max(time, answer[i]);
            }

            sb.append(infected).append(" ").append(time).append("\n");
        }

        System.out.print(sb);
    }
}
class Node implements Comparable<Node>{
    int vertex, time;

    Node(int vertex, int time){
        this.vertex = vertex;
        this.time = time;
    }

    @Override
    public int compareTo(Node a){
        return this.time - a.time;
    }
}