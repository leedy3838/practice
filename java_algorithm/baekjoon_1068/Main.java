package baekjoon_1068;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            parent[i] = Integer.parseInt(st.nextToken());

        int remove = Integer.parseInt(br.readLine());
        removeNode(remove);

        System.out.println(countLeaf());
    }

    static void removeNode(int idx){
        parent[idx] = -2;
        visited[idx] = true;

        for(int i = 0; i<N; i++){
            if(parent[i] == idx)
                removeNode(i);
        }
    }

    static int countLeaf(){
        int sum = 0;

        for(int i = 0; i<N; i++){
            if(visited[i])
                continue;

            if(isLeaf(i))
                sum++;
        }

        return sum;
    }

    static boolean isLeaf(int idx){
        visited[idx] = true;

        boolean leafTrue = true;
        for(int i = 0; i<N; i++){
            if (parent[i] == idx) {
                leafTrue = false;
                break;
            }
        }

        return leafTrue;
    }
}