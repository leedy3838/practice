package baekjoon_14940;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, newMap;
    static boolean[][] isVisit;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int startR = -1;
        int startC = -1;

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2){
                    startR = i;
                    startC = j;
                }
            }
        }

        newMap = new int[N][M];
        isVisit = new boolean[N][M];

        makeMap(startR, startC);

        printMap();
    }

    static void makeMap(int startR, int startC){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startR, startC, 0));
        isVisit[startR][startC] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if(dr<0||dc<0||dr>=N||dc>=M)
                    continue;
                if(isVisit[dr][dc])
                    continue;
                if(map[dr][dc] == 0)
                    continue;


                newMap[dr][dc] = node.val + 1;
                q.offer(new Node(dr, dc, node.val + 1));
                isVisit[dr][dc] = true;
            }
        }
    }

    static void printMap(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                if(isVisit[i][j])
                    sb.append(newMap[i][j]).append(" ");
                else if(map[i][j] == 1)
                    sb.append(-1).append(" ");
                else
                    sb.append(0).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static class Node{
        int row, col, val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}
