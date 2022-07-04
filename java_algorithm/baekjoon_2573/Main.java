package baekjoon_2573;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
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
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int year = icebergCount();
        System.out.println(year);
    }

    static int icebergCount(){
        int year;
        int loop = 0;

        //빙산의 갯수 확인
        while(true){
            visited = new boolean[N][M];
            int icebergCount = 0;

            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(map[i][j] == 0)
                        continue;
                    if(visited[i][j])
                        continue;

                    icebergFind(i, j);
                    icebergCount++;
                }
            }

            // 빙산이 없거나 2개 이상이면 break
            if(icebergCount == 0) {
                year = 0;
                break;
            }
            else if(icebergCount != 1){
                year = loop;
                break;
            }

            icebergMelt();
            loop++;
        }

        return year;
    }

    static void icebergFind(int row, int col){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col, 0));
        visited[row][col] = true;

        while(!q.isEmpty()){
            Node a = q.poll();

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<0||dc<0||dr>=N||dc>=M)
                    continue;
                if(visited[dr][dc])
                    continue;
                if(map[dr][dc] == 0)
                    continue;

                visited[dr][dc] = true;
                q.offer(new Node(dr, dc));
            }
        }
    }

    static void icebergMelt(){
        Queue<Node> q = new LinkedList<>();

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(map[i][j] != 0){
                    int extent = meltExtent(i, j);

                    q.offer(new Node(i, j, extent));
                }
            }
        }

        while(!q.isEmpty()){
            Node a = q.poll();
            map[a.row][a.col] -= a.edge;

            if(map[a.row][a.col]<0)
                map[a.row][a.col] = 0;
        }
    }

    static int meltExtent(int row, int col){
        int extent = 0;

        for(int i = 0; i<4; i++){
            int dr = row + dR[i];
            int dc = col + dC[i];

            if(dr<0||dc<0||dr>=N||dc>=M)
                continue;

            if(map[dr][dc] == 0)
                extent++;
        }

        return extent;
    }
}
class Node{
    int row, col, edge;

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }
    Node(int row, int col, int edge){
        this.row = row;
        this.col = col;
        this.edge = edge;
    }
}