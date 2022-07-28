package baekjoon_19238;

import java.io.*;
import java.util.*;

public class Main {
    static int N, gas;
    static boolean canGo = true;
    static int[][] map;
    static Node[] start;
    static Node[] arrival;
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        start = new Node[M+1];
        arrival = new Node[M+1];

        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1)
                    map[i][j] = Integer.MAX_VALUE;
            }
        }

        st = new StringTokenizer(br.readLine());

        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());

        for(int i = 1; i<=M; i++){
            st = new StringTokenizer(br.readLine());

            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            int arrivalR = Integer.parseInt(st.nextToken());
            int arrivalC = Integer.parseInt(st.nextToken());

            map[startR][startC] = i;
            //고객의 출발, 도착지를 저장
            start[i] = new Node(startR, startC);
            arrival[i] = new Node(arrivalR, arrivalC);
        }

        while(M-->0){
            int consumer = consumerFind(startRow, startCol);

            //consumer이 0일 때는 벽에 막혀 찾을 수 없을 때
            if(!canGo || consumer == 0) {
                System.out.println(-1);
                return;
            }

            goToArrival(start[consumer].row, start[consumer].col
                    , arrival[consumer].row, arrival[consumer].col);

            startRow = arrival[consumer].row;
            startCol = arrival[consumer].col;

            if(!canGo) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(gas);
    }

    static int consumerFind(int startRow, int startCol){
        boolean[][] visited = new boolean[N+1][N+1];

        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(startRow, startCol, 0));
        visited[startRow][startCol] = true;

        while (!q.isEmpty()){
            Node vertex = q.poll();
            //손님을 만나면 return
            if(map[vertex.row][vertex.col] != 0){
                gas -= vertex.dist;
                if(gas < 0)
                    canGo = false;

                int returnVal = map[vertex.row][vertex.col];
                map[vertex.row][vertex.col] = 0;

                return returnVal;
            }

            for(int i = 0; i<4; i++){
                int dr = vertex.row + dR[i];
                int dc = vertex.col + dC[i];

                if(dr<1||dc<1||dr>N||dc>N)
                    continue;
                if(map[dr][dc] == Integer.MAX_VALUE)
                    continue;
                if(visited[dr][dc])
                    continue;

                visited[dr][dc] = true;
                q.offer(new Node(dr, dc, vertex.dist + 1));
            }
        }

        return 0;
    }

    static void goToArrival(int startRow, int startCol, int targetR, int targetC){
        boolean[][] visited = new boolean[N+1][N+1];

        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(startRow, startCol, 0));
        visited[startRow][startCol] = true;

        while (!q.isEmpty()){
            Node vertex = q.poll();

            //목적지에 도착하면
            if(vertex.row == targetR && vertex.col == targetC){
                gas -= vertex.dist;
                if(gas < 0)
                    canGo = false;

                gas += vertex.dist*2;
                return;
            }

            for(int i = 0; i<4; i++){
                int dr = vertex.row + dR[i];
                int dc = vertex.col + dC[i];

                if(dr<1||dc<1||dr>N||dc>N)
                    continue;
                if(map[dr][dc] == Integer.MAX_VALUE)
                    continue;
                if(visited[dr][dc])
                    continue;

                visited[dr][dc] = true;
                q.offer(new Node(dr, dc, vertex.dist + 1));
            }
        }

        //도착지로 갈 수 없었음을 의미
        canGo = false;
    }
}
class Node implements Comparable<Node>{
    int row, col, dist;

    @Override
    public int compareTo(Node a) {
        if(this.dist == a.dist){
            if(this.row == a.row)
                return this.col - a.col;

            return this.row - a.row;
        }

        return this.dist - a.dist;
    }

    Node(int row, int col){
        this.row = row;
        this.col = col;
    }

    Node(int row, int col, int dist){
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}