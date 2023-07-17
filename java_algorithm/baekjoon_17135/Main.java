package baekjoon_17135;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D;
    static boolean[][] isEnemyHere;
    static boolean[][] visited;
    //이전 턴에 잡은 적인지 확인
    static boolean[][] alreadySearched;
    //left, up, right
    static int[] dR = {0, -1, 0};
    static int[] dC = {-1, 0, 1};

    public static void main(String[] args) throws IOException{
        firstSetting();

        System.out.println(executeGame());
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        isEnemyHere = new boolean[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<M; j++)
                isEnemyHere[i][j] = (Integer.parseInt(st.nextToken()) == 1);
        }
    }

    private static int executeGame() {
        int count = 0;

        //N+1번 줄에 궁수가 올 수 있는 경우 모두 실행
        for(int i = 0; i<M-2; i++){
            for(int j = i+1; j<M-1; j++){
                for(int k = j+1; k<M; k++){
                    visited = new boolean[N][M];
                    alreadySearched = new boolean[N][M];

                    count = Math.max(count, game(i, j, k));
                }
            }
        }

        return count;
    }

    //죽일 수 있는 적의 수를 return
    private static int game(int firstArcher, int secondArcher, int thirdArcher){
        int count = 0;

        for(int nowRow = N-1; nowRow>=0; nowRow--){
            killNode first = checkPerArcher(nowRow, firstArcher);
            killNode second = checkPerArcher(nowRow, secondArcher);
            killNode third = checkPerArcher(nowRow, thirdArcher);

            count += first.add;
            count += second.add;
            count += third.add;

            if(first.add == 1)
                alreadySearched[first.row][first.col] = true;
            if(second.add == 1)
                alreadySearched[second.row][second.col] = true;
            if(third.add == 1)
                alreadySearched[third.row][third.col] = true;
        }

        return count;
    }

    private static killNode checkPerArcher(int archerRow, int archerCol) {
        int add = 0;
        int tRow = -1;
        int tCol = -1;

        //거리가 1에 적이 존재
        if(isEnemyHere[archerRow][archerCol] && !alreadySearched[archerRow][archerCol]){
            if(!visited[archerRow][archerCol]){
                visited[archerRow][archerCol] = true;
                add = 1;

                tRow = archerRow;
                tCol = archerCol;
            }
        }

        else{
            Node target = perArcherSearch(archerRow, archerCol);

            //적을 찾지 못함
            if(target.row == -1 && target.col == -1)
                return new killNode(tRow, tCol, 0);

            if(!visited[target.row][target.col]){
                visited[target.row][target.col] = true;
                add = 1;

                tRow = target.row;
                tCol = target.col;
            }
        }

        return new killNode(tRow, tCol, add);
    }

    private static Node perArcherSearch(int archerRow, int archerCol){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(archerRow, archerCol, 1));

        //이번 탐색에서 이미 진행함
        boolean[][] alreadyGo = new boolean[N][M];
        alreadyGo[archerRow][archerCol] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i = 0; i<3; i++){
                int dr = now.row + dR[i];
                int dc = now.col + dC[i];
                int len = now.len + 1;

                if(dr<0||dc<0||dr>=N||dc>=M)
                    continue;
                if(alreadyGo[dr][dc])
                    continue;
                if(len>D)
                    return new Node(-1, -1, -1);

                //적을 찾음
                if(isEnemyHere[dr][dc] && !alreadySearched[dr][dc])
                    return new Node(dr, dc, len);
                else{
                    alreadyGo[dr][dc] = true;
                    q.offer(new Node(dr, dc, len));
                }
            }
        }

        //적을 찾지 못함
        return new Node(-1, -1, -1);
    }

    private static class Node{
        int row, col, len;

        public Node(int row, int col, int len) {
            this.row = row;
            this.col = col;
            this.len = len;
        }
    }

    private static class killNode{
        int row, col, add;

        public killNode(int row, int col, int add) {
            this.row = row;
            this.col = col;
            this.add = add;
        }
    }
}
