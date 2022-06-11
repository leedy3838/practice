package baekjoon_14938;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int max = Integer.MIN_VALUE;
    static int[] value;
    static int[] dijkstra;
    static boolean[] visited;
    static List<List<Node>> l;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        value = new int[N+1];
        dijkstra = new int[N+1];
        visited = new boolean[N+1];

        l = new ArrayList<>();
        for(int i = 0; i<=N; i++)
            l.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++)
            value[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i<R; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            l.get(A).add(new Node(B, len));
            l.get(B).add(new Node(A, len));
        }

        bfs();

        System.out.println(max);
    }

    static void bfs(){
        for(int i = 1; i<=N; i++) {
            for(int j = 1; j<=N; j++) {
                dijkstra[j] = Integer.MAX_VALUE;
                visited[j] = false;
            }

            int val = bfs(i);
            max = Math.max(max , val);
        }
    }

    static int bfs(int start){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        dijkstra[start] = 0;

        int sum = value[start];

        while(!q.isEmpty()){
            Node num = q.poll();
            int nowIdx = num.idx;
            int nowLen = num.len;

            for(int i = 0; i<l.get(nowIdx).size(); i++){
                Node next = l.get(nowIdx).get(i);

                int nextIdx = next.idx;
                int nextLen = next.len;

                if(nowLen + nextLen>M)
                    continue;
                if(dijkstra[nextIdx] < nowLen + nextLen)
                    continue;

                if(!visited[nextIdx])
                    sum += value[nextIdx];

                dijkstra[nextIdx] = nextLen + nowLen;
                visited[nextIdx] = true;
                q.offer(new Node(nextIdx, nowLen + nextLen));
            }
        }

        return sum;
    }
}
class Node{
    int idx, len;

    Node(int idx, int len){
        this.idx = idx;
        this.len = len;
    }
}
