package baekjoon_21736;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dR = {0, 0, 1, -1};
    static int[] dC = {1, -1, 0, 0};
    static char[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int startR = 0;
        int startC = 0;

        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < M; j++){
                map[i][j] = input.charAt(j);

                if(map[i][j] == 'I'){
                    startR = i;
                    startC = j;
                }
            }
        }

        int ans = bfs(startR, startC);

        if(ans == 0)
            System.out.println("TT");
        else
            System.out.println(ans);
    }

    static int bfs(int startR, int startC){
        boolean[][] isVisit = new boolean[N][M];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startR, startC));
        isVisit[startR][startC] = true;

        int cnt = 0;

        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i = 0; i < 4; i++){
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if(dr<0||dc<0||dr>=N||dc>=M)
                    continue;
                if(isVisit[dr][dc])
                    continue;
                if(map[dr][dc] == 'X')
                    continue;

                if(map[dr][dc] == 'P')
                    cnt++;

                q.offer(new Node(dr, dc));
                isVisit[dr][dc] = true;
            }
        }

        return cnt;
    }

    static class Node{
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}