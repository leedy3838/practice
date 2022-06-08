package baekjoon_1967;

import java.util.*;
import java.io.*;

public class Main {
    static List<Node>[] map;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        map = new List[n+1];

        for(int i = 1; i<=n; i++)
            map[i] = new ArrayList<>();

        for(int i = 1; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map[start].add(new Node(end, val));
            map[end].add(new Node(start, val));
        }

        dfs();

        System.out.println(max);
    }

    static void dfs(){
        for(int i = 1; i<=n; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, 0);
                visited[i] = false;
            }
        }
    }

    static void dfs(int ex, int sum){
        for(int i = 0; i<map[ex].size(); i++){
            Node next = map[ex].get(i);
            int nextGo = next.go;
            int val = next.val;

            if(!visited[nextGo]){
                visited[nextGo] = true;
                dfs(nextGo, sum + val);
                visited[nextGo] = false;
            }
        }

        max = Math.max(max, sum);
    }
}

class Node{
    int go, val;

    Node(int go, int val){
        this.go = go;
        this.val = val;
    }
}