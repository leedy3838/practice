package baekjoon_2589;

import java.io.*;
import java.util.*;

public class Main {
    static int row, col;
    static char[][] map;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};
    static int max = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        boolean[][] visited = new boolean[row][col];

        for(int i = 0; i<row; i++){
            String input = br.readLine();

            for(int j = 0; j<col; j++) {
                map[i][j] = input.charAt(j);

                if(map[i][j] == 'W')
                    visited[i][j] = true;
            }
        }

        treasureFind(visited);

        System.out.println(max);
    }

    static void treasureFind(boolean[][] visited){
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(visited[i][j])
                    continue;

                //원본을 바꾸지 않게 새로운 visited를 만들어서 보내줌
                boolean[][] visitedCopy = new boolean[row][];
                for(int k = 0; k<row; k++)
                    visitedCopy[k] = visited[k].clone();

                treasureFind(i, j, visitedCopy);
            }
        }
    }

    static void treasureFind(int startRow, int startCol, boolean[][] visited){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startRow, startCol, 0));
        visited[startRow][startCol] = true;

        while(!q.isEmpty()){
            Node a = q.poll();
            max = Math.max(max, a.cnt);

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<0||dc<0||dr>=row||dc>=col)
                    continue;
                if(visited[dr][dc])
                    continue;

                q.offer(new Node(dr, dc, a.cnt+1));
                visited[dr][dc] = true;
            }
        }
    }
}
class Node{
    int row, col, cnt;

    Node(int row, int col, int cnt){
        this.row = row;
        this.col = col;
        this.cnt = cnt;
    }
}