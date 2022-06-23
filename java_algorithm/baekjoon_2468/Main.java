package baekjoon_2468;

import java.io.*;
import java.util.*;

public class Main {
    static int N, safe = 0;
    static int minHeight = Integer.MAX_VALUE;
    static int maxHeight = Integer.MIN_VALUE;
    static int[][] map;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        for(int i = minHeight-1 ; i<=maxHeight; i++)
            safeArea(i);

        System.out.println(safe);
    }

    static void safeArea(int height){
        boolean[][] visited = new boolean[N][N];
        int safeCount = 0;

        for(int i = 0; i<N; i++)
            for(int j = 0; j<N; j++)
                if(map[i][j] <= height)
                    visited[i][j] = true;

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(!visited[i][j]){
                    Queue<Node> q = new LinkedList<>();
                    q.offer(new Node(i, j));
                    safeCount++;

                    while(!q.isEmpty()){
                        Node a = q.poll();
                        visited[a.row][a.col] = true;

                        for(int k = 0; k<4; k++){
                            int dr = a.row + dR[k];
                            int dc = a.col + dC[k];

                            if(dr<0||dc<0||dr>=N||dc>=N)
                                continue;
                            if(visited[dr][dc])
                                continue;

                            q.offer(new Node(dr, dc));
                            visited[dr][dc] = true;
                        }
                    }
                }
            }
        }

        safe = Math.max(safe, safeCount);
    }
}
class Node{
    int row, col;

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}