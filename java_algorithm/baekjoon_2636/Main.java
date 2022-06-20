package baekjoon_2636;

import java.io.*;
import java.util.*;

public class Main {
    static int row, col, cheese, time = 0;
    static int[][] map;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        cheese = row * col;

        map = new int[row][col];

        for(int i = 0; i<row; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0)
                    cheese--;
            }
        }

        if(cheese == 0){
            System.out.println(0);
            System.out.println(0);
            return;
        }

        bfs();

        int sum = 0;
        for(int i = 0; i<row; i++)
            for(int j = 0; j<col; j++)
                if(map[i][j] == 1)
                    sum++;

        System.out.println(time);
        System.out.println(sum);
    }

    static void bfs(){


        while(true) {
            Queue<Node> q = new LinkedList<>();
            Queue<Node> remove = new LinkedList<>();
            q.offer(new Node(0, 0));

            //밖의 공기와 맞닿은 치즈들을 확인
            boolean[][] visited = new boolean[row][col];
            visited[0][0] = true;
            while(!q.isEmpty()){
                Node a = q.poll();

                for(int i = 0; i<4; i++){
                    int dr = a.row + dR[i];
                    int dc = a.col + dC[i];

                    if(dr<0||dc<0||dr>=row||dc>=col)
                        continue;
                    if(visited[dr][dc])
                        continue;

                    if(map[dr][dc] == 0){
                        visited[dr][dc] = true;
                        q.offer(new Node(dr, dc));
                    }
                    if(map[dr][dc] == 1) {
                        visited[dr][dc] = true;
                        remove.offer(new Node(dr, dc));
                        cheese--;
                    }
                }
            }

            time++;
            if (cheese == 0)
                break;

            //공기와 맞닿은 치즈들을 제거
            while (!remove.isEmpty()) {
                Node a = remove.poll();
                map[a.row][a.col] = 0;
            }
        }
    }
}
class Node{
    int row, col;

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}