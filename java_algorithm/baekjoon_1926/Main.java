package baekjoon_1926;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cnt = 0, max = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(visited[i][j])
                    continue;
                if(map[i][j] == 0)
                    continue;

                bfs(i, j);
            }
        }

        System.out.println(cnt);
        System.out.println(max);
    }

    static void bfs(int row, int col){
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(row, col));
        visited[row][col] = true;
        int breadth = 1;
        cnt++;

        while(!q.isEmpty()) {
            Node a = q.poll();

            for (int i = 0; i < 4; i++) {
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if (dr < 0 || dc < 0 || dr >= N || dc >= M)
                    continue;
                if(map[dr][dc] == 0)
                    continue;
                if (visited[dr][dc])
                    continue;

                breadth++;
                visited[dr][dc] = true;
                q.offer(new Node(dr, dc));
            }
        }

        max = Math.max(max, breadth);
    }
}
class Node{
    int row, col;

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}