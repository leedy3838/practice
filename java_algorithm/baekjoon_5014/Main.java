package baekjoon_5014;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //F 총 층수, S 현재 위치, G 목표 위치
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] dir = new int[2];
        dir[0] = U;
        dir[1] = -D;

        boolean[] visited = new boolean[F+1];
        int cnt = 0;
        boolean canGo = false;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(S, 0));
        visited[S] = true;

        while(!q.isEmpty()){
            Node nowFloor = q.poll();

            if(nowFloor.vertex == G){
                canGo = true;
                cnt = nowFloor.cnt;
                break;
            }

            for(int i = 0; i<2; i++){
                int nextFloor = nowFloor.vertex + dir[i];

                if(nextFloor<1 ||nextFloor>F)
                    continue;
                if(visited[nextFloor])
                    continue;

                q.offer(new Node(nextFloor, nowFloor.cnt+1));
                visited[nextFloor] = true;
            }
        }

        if(canGo)
            System.out.println(cnt);
        else
            System.out.println("use the stairs");
    }
}
class Node{
    int vertex, cnt;

    Node(int vertex, int cnt){
        this.vertex = vertex;
        this.cnt = cnt;
    }
}