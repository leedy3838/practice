package baekjoon_14502;

import java.util.*;
import java.io.*;

public class Main{
    static int N, M;
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    static int[] drow = {0, 0, 1, -1};
    static int[] dcol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<M; j++)
                map[i][j]= Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(max);
    }

    static void dfs(int count){
        if(count == 3){
            bfs();
            return;
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(map[i][j] == 1 || map[i][j] == 2)
                    continue;

                map[i][j] = 1;
                dfs(count+1);
                map[i][j] = 0;
            }
        }
    }

    static void bfs(){
        Queue<rowCol> ll = new LinkedList<>();

        for(int i = 0; i<N; i++)
            for(int j = 0; j<M; j++)
                if(map[i][j] == 2)
                    ll.offer(new rowCol(i, j));

        //원본 데이터를 훼손하지 않기 위해서 새로운 배열에 데이터 저장
        // sum = map을 하지 않는 이유는 shallow copy가 일어나기 때문에 clone()으로 deep copy
        int[][] sub = new int[N][M];

        for(int i = 0; i<N; i++)
            sub[i] = map[i].clone();

        while(!ll.isEmpty()){
            rowCol rc = ll.poll();

            for(int i = 0; i<4; i++){
                int dr = rc.row + drow[i];
                int dc = rc.col + dcol[i];

                if(dr<0||dc<0||dr>=N||dc>=M)
                    continue;

                if(sub[dr][dc] == 0){
                    ll.offer(new rowCol(dr, dc));
                    sub[dr][dc] = 2;
                }
            }
        }

        check(sub);
    }

    static void check(int[][] sub){
        int cnt = 0;

        for(int i = 0; i<N; i++)
            for(int j = 0; j<M; j++)
                if(sub[i][j] == 0)
                    cnt++;

        max = Math.max(max, cnt);
    }
} 

//좌표를 저장하기 쉽게 하지 위해서 새로운 클래스 생성
class rowCol{
    int row, col;
    rowCol(int row, int col){
        this.row = row;
        this.col = col;
    }
}
