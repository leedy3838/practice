package baekjoon_1197;

import java.util.*;
import java.io.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Node> l = new ArrayList<>();

        for(int i = 0; i<E; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            l.add(new Node(A, B, val));
        }

        Collections.sort(l);

        int sum = 0;
        parent = new int[V+1];
        for(int i = 1; i<=V; i++)
            parent[i] = i;

        //kruskal 알고리즘
        for(Node a : l){
            int A = a.A;
            int B = a.B;
            int val = a.val;

            if(find(A) == find(B))
                continue;

            sum += val;
            union(A, B);
        }

        System.out.println(sum);
    }

    static int find(int A){
        if(parent[A] == A)
            return A;
        else
            return find(parent[A]);

    }

    static void union(int A, int B){
        int a = find(A);
        int b = find(B);

        if(a != b)
            parent[b] = a;
    }
}
class Node implements Comparable<Node>{
    int A, B, val;

    Node(int A, int B, int val){
        this.A = A;
        this.B = B;
        this.val = val;
    }

    @Override
    public int compareTo(Node a){
        int val = a.val;

        if(this.val>val)
            return 1;
        else
            return -1;
    }
}