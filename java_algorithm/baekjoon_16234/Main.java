package baekjoon_16234;

import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];


        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int day = 0;
        while(populationMove() != -1)
            day++;

        System.out.println(day);
    }

    static int populationMove(){
        boolean[][] visited = new boolean[N][N];
        boolean moved = false;

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(visited[i][j])
                    continue;

                Queue<Node> check = new LinkedList<>();
                List<Node> change = new LinkedList<>();

                check.offer(new Node(i, j));
                int sum = 0;
                int count = 0;

                while(!check.isEmpty()) {
                    Node a = check.poll();
                    change.add(a);
                    visited[a.row][a.col] = true;

                    count++;
                    sum += map[a.row][a.col];

                    for (int k = 0; k < 4; k++) {
                        int dr = a.row + dR[k];
                        int dc = a.col + dC[k];

                        if (dr < 0 || dc < 0 || dr >= N || dc >= N)
                            continue;
                        if (visited[dr][dc])
                            continue;

                        int gap = Math.abs(map[a.row][a.col] - map[dr][dc]);

                        if(gap>=L && gap<=R) {
                            check.offer(new Node(dr, dc));
                            visited[dr][dc] = true;
                            moved = true;
                        }
                    }
                }
                while(!change.isEmpty()){
                    Node a = change.remove(0);
                    map[a.row][a.col] = sum/count;
                }
            }
        }

        if(!moved)
            return -1;
        else
            return 1;
    }
}
class Node{
    int row, col;

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}
