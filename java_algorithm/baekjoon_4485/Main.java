package baekjoon_4485;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int cnt = 0;

        while(true){
            int N = Integer.parseInt(br.readLine());
            cnt++;

            if(N == 0)
                break;

            int[][] cave = new int[N][N];
            int[][] thiefLoopy = new int[N][N];

            //cave와 thiefLoopy 초기화
            for(int i = 0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int j = 0; j<N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    thiefLoopy[i][j] = -1;
                }
            }

            Queue<Node> q = new PriorityQueue<>();
            q.offer(new Node(0, 0, cave[0][0]));
            thiefLoopy[0][0] = cave[0][0];

            while(!q.isEmpty()){
                Node a = q.poll();

                if(a.row == N-1 && a.col == N-1){
                    sb.append("Problem ").append(cnt).append(": ").append(a.loopy).append("\n");
                    break;
                }

                for(int i = 0; i<4; i++){
                    int dr = a.row + dR[i];
                    int dc = a.col + dC[i];

                    if(dr<0||dc<0||dr>=N||dc>=N)
                        continue;
                    if(thiefLoopy[dr][dc] >= a.loopy + cave[dr][dc])
                        continue;

                    thiefLoopy[dr][dc] = a.loopy + cave[dr][dc];
                    q.offer(new Node(dr, dc, thiefLoopy[dr][dc]));
                }
            }
        }

        System.out.print(sb);
    }
}
class Node implements Comparable<Node>{
    int row, col, loopy;

    Node(int row, int col, int loopy){
        this.row = row;
        this.col = col;
        this.loopy = loopy;
    }

    @Override
    public int compareTo(Node a){
        return this.loopy - a.loopy;
    }
}