package baekjoon_16236;

import java.io.*;
import java.util.*;

public class Main {
    static int N, sharkRow, sharkCol, sharkSize = 2, time, eatCount = 0;
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

                if(map[i][j] == 9){
                    sharkRow = i;
                    sharkCol = j;

                    map[i][j] = 0;
                }
            }
        }

        int timeSum = 0;
        while(sharkGo())
            timeSum += time;

        System.out.println(timeSum);
    }

    static boolean sharkGo(){
        time = 0;
        if(eatCount == sharkSize){
            eatCount = 0;
            sharkSize++;
        }

        boolean[][] visited = new boolean[N][N];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sharkRow, sharkCol, 0));
        visited[sharkRow][sharkCol] = true;

        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        int minTime = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Node a = q.poll();
            if(a.time >= minTime)
                break;

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<0||dc<0||dr>=N||dc>=N)
                    continue;
                if(visited[dr][dc])
                    continue;
                if(map[dr][dc] > sharkSize)
                    continue;

                //아기 상어가 먹을 수 있는 물고기가 있는 칸에 들어옴
                if(map[dr][dc]>0 && map[dr][dc]<sharkSize){
                    if(dr<minRow){
                        minRow = dr;
                        minCol = dc;
                        minTime = a.time + 1;
                    }
                    else if(dr == minRow){
                        if(dc < minCol){
                            minCol = dc;
                            minTime = a.time + 1;
                        }
                    }
                }

                q.offer(new Node(dr, dc, a.time+1));
                visited[dr][dc] = true;
            }
        }

        if(minTime == Integer.MAX_VALUE)
            return false;
        else {
            sharkRow = minRow;
            sharkCol = minCol;
            eatCount++;
            time = minTime;
            map[sharkRow][sharkCol] = 0;

            return true;
        }
    }
}
class Node{
    int row, col, time;

    Node(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }
}