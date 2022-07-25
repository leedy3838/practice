package baekjoon_2637;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] needNum;
    static int[] parentNum;
    static List<List<Node>> l = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        needNum = new int[N+1];
        parentNum = new int[N+1];
        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        while(M-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int need = Integer.parseInt(st.nextToken());

            l.get(A).add(new Node(B, need));
            parentNum[B]++;
        }

        topologicalSort();

        for(int i = 1; i<N; i++)
            if(l.get(i).size() == 0)
                System.out.println(i+" "+needNum[i]);
    }

    static void topologicalSort(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        needNum[N] = 1;

        while(!q.isEmpty()){
            int vertex = q.poll();

            for(int i = 0; i<l.get(vertex).size(); i++){
                Node nextV = l.get(vertex).get(i);

                needNum[nextV.vertex] += needNum[vertex] * nextV.need;
                parentNum[nextV.vertex]--;

                if(parentNum[nextV.vertex] == 0)
                    q.offer(nextV.vertex);
            }
        }
    }
}
class Node{
    int need, vertex;

    Node(int vertex, int need){
        this.vertex = vertex;
        this.need = need;
    }
}