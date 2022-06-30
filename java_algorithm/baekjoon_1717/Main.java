package baekjoon_1717;

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i = 1; i<=N; i++)
            parent[i] = i;

        while(M-->0){
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(type == 0)
                union(A, B);

            else if (type == 1){
                int parentA = find(A);
                int parentB = find(B);

                if(parentA == parentB)
                    sb.append("YES").append("\n");
                else
                    sb.append("NO").append("\n");
            }
        }

        System.out.print(sb);
    }

    static int find(int idx){
        if(parent[idx] == idx)
            return idx;
        else
            return find(parent[idx]);
    }
    static void union(int idx1, int idx2){
        int parent1 = find(idx1);
        int parent2 = find(idx2);

        if(parent1 != parent2)
            parent[parent2] = parent1;
    }
}
