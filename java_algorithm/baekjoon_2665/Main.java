package baekjoon_2665;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split("");

            for(int j = 0; j<N; j++)
                map[i][j] = Integer.parseInt(input[j]);
        }

        int count = findNum();
        System.out.println(count);
    }

    static int findNum(){
        boolean[][] visited = new boolean[N][N];
        int count = 0;

        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node a = q.poll();

            if(a.row == N-1 && a.col == N-1) {
                count = a.blackCount;
                break;
            }

            for(int i = 0; i<4; i++){
                int dr = a.row + dR[i];
                int dc = a.col + dC[i];

                if(dr<0||dc<0||dr>=N||dc>=N)
                    continue;
                if(visited[dr][dc])
                    continue;

                if(map[dr][dc] == 0)
                    q.offer(new Node(dr, dc, a.blackCount+1));
                else
                    q.offer(new Node(dr, dc, a.blackCount));

                visited[dr][dc] = true;
            }
        }

        return count;
    }
}
class Node implements Comparable<Node>{
    int row, col, blackCount;

    Node(int row, int col, int blackCount){
        this.row = row;
        this.col = col;
        this.blackCount = blackCount;
    }

    @Override
    public int compareTo(Node a){
        return this.blackCount - a.blackCount;
    }
}