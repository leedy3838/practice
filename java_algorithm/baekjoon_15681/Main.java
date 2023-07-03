package baekjoon_15681;

import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> l = new ArrayList<>();
    static int[] vertexCount;
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());
        vertexCount = new int[N+1];

        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            l.get(B).add(A);
        }

        findVertexNum(R);

        for(int i = 0; i<Q; i++){
            int vertex = Integer.parseInt(br.readLine());

            sb.append(vertexCount[vertex]).append("\n");
        }

        System.out.print(sb);
    }

    static void findVertexNum(int root){
        vertexCount[root] = 1;

        for(int nextV : l.get(root))
            vertexCount[root] += findVertexNum(root, nextV);
    }

    static int findVertexNum(int prevV, int vertex){
        vertexCount[vertex] = 1;

        for(int nextV : l.get(vertex)){
            if(nextV == prevV)
                continue;

            vertexCount[vertex] += findVertexNum(vertex, nextV);
        }

        return vertexCount[vertex];
    }
}
