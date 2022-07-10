package baekjoon_1743;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C, max = Integer.MIN_VALUE;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];
        visited = new boolean[R+1][C+1];

        while(K-->0){
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;
        }

        bfs();

        System.out.println(max);
    }

    static void bfs(){
        for(int i = 1; i<=R; i++){
            for(int j = 1; j<=C; j++){
                if(map[i][j] == 0)
                    continue;
                if(visited[i][j])
                    continue;

                bfs(i, j);
            }
        }
    }

    static void bfs(int row, int col){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col));
        visited[row][col] = true;
        int cnt = 0;

        while(!q.isEmpty()) {
            Node a = q.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if (dr < 1 || dc < 1 || dr > R || dc > C)
                    continue;
                if (visited[dr][dc])
                    continue;
                if (map[dr][dc] == 0)
                    continue;

                visited[dr][dc] = true;
                q.offer(new Node(dr, dc));
            }
        }

        max = Math.max(max, cnt);
    }
}
class Node{
    int row, col;

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}