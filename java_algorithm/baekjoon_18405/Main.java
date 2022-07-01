package baekjoon_18405;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j<=N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int time = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        while(time-->0){
            if(map[row][col] != 0)
                break;

            diffusion();
        }

        System.out.println(map[row][col]);
    }

    static void diffusion(){
        Queue<Node> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[N+1][N+1];

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(map[i][j] != 0){
                    visited[i][j] = true;
                    q.offer(new Node(i, j, map[i][j]));
                }
            }
        }

        while(!q.isEmpty()){
            Node a = q.poll();

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<1||dc<1||dr>N||dc>N)
                    continue;
                if(visited[dr][dc])
                    continue;

                map[dr][dc] = a.val;
                visited[dr][dc] = true;
            }
        }
    }
}
class Node implements Comparable<Node>{
    int row, col, val;

    Node(int row, int col, int val){
        this.row = row;
        this.col = col;
        this.val = val;
    }

    @Override
    public int compareTo(Node a){
        return this.val - a.val;
    }
}