package baekjoon_1261;

import java.io.*;
import java.util.*;


public class Main {
    static int row, col;
    static int[][] map;
    static boolean[][] visited;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        visited = new boolean[row][col];

        for(int i = 0; i<row; i++){
            String[] input = br.readLine().split("");

            for(int j = 0; j<col; j++)
                map[i][j] = Integer.parseInt(input[j]);
        }

        System.out.println(pathFind(0, 0));
    }

    static int pathFind(int startRow, int startCol){
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(startRow, startCol, 0));
        visited[startRow][startCol] = true;

        while(!q.isEmpty()){
            Node a = q.poll();

            if(a.row == row-1 && a.col == col-1) {
                return a.breakWall;
            }

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<0||dc<0||dr>=row||dc>=col)
                    continue;
                if(visited[dr][dc])
                    continue;

                if(map[dr][dc] == 0)
                    q.offer(new Node(dr, dc, a.breakWall));
                else
                    q.offer(new Node(dr, dc, a.breakWall+1));

                visited[dr][dc] = true;
            }
        }

        return 0;
    }
}
class Node implements Comparable<Node>{
    int row, col, breakWall;

    Node(int row, int col, int breakWall){
        this.row = row;
        this.col = col;
        this.breakWall = breakWall;
    }

    @Override
    public int compareTo(Node a){
        return this.breakWall - a.breakWall;
    }
}
