package baekjoon_14503;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int count = 0;
    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        pathFind(r, c, dir);

        System.out.println(count);
    }

    static void pathFind(int r, int c, int dir){
        int nowR = r;
        int nowC = c;
        int nowDir = dir;

        int stop = 0;

        while(true){
            if(stop == 4){
                int backDir = nowDir-2<0 ? nowDir+2 : nowDir-2;
                int backR = nowR + dr[backDir];
                int backC = nowC + dc[backDir];

                if(map[backR][backC] == 1)
                    break;
                else{
                    nowR = backR;
                    nowC = backC;
                    stop = 0;
                }
            }

            if(!visited[nowR][nowC]){
                visited[nowR][nowC] = true;
                count++;
            }

            int nextDir = nowDir-1<0 ? nowDir+3 : nowDir-1;
            int nextR = nowR + dr[nextDir];
            int nextC = nowC + dc[nextDir];

            //왼쪽에 청소하지 않은 빈 공간이 있을 때
            if(!visited[nextR][nextC] && map[nextR][nextC] == 0){
                nowR = nextR;
                nowC = nextC;
                nowDir = nextDir;
                stop = 0;
            }
            else{
                nowDir = nextDir;
                stop++;
            }
        }
    }
}
