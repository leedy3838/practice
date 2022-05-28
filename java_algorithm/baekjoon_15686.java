import java.util.*;
import java.io.*;

public class Main{
    static int N, M;
    static int[][] map;
    static LinkedList<rowCol> chicken = new LinkedList<>();
    static int min = Integer.MAX_VALUE;
    static int[] drow = {0, 0, 1, -1};
    static int[] dcol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++) {
                int space = Integer.parseInt(st.nextToken());
                map[i][j] = space;

                if(space == 2) {
                    chicken.add(new rowCol(i, j, 0));
                    map[i][j] = 0;
                }
            }
        }

        dfs(0, 0);
        System.out.println(min);
    }

    static void dfs(int count, int idx){
        if(count == M){
            bfs();
            return;
        }

        for(int i = idx; i<chicken.size(); i++){
            rowCol rc = chicken.get(i);
            int row = rc.row;
            int col = rc.col;

            if(map[row][col] == 2)
                continue;

            map[row][col] = 2;
            dfs(count+1, i);
            map[row][col] = 0;
        }
    }

    static void bfs(){
        int[][] sub = new int[N][N];
        Queue<rowCol> q = new LinkedList<>();
        int sum = 0;

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sub[i][j] = map[i][j];

                if(sub[i][j] == 2)
                    q.offer(new rowCol(i, j, 0));
            }
        }

        while(!q.isEmpty()){
            rowCol a = q.poll();

            for(int i = 0; i<4; i++){
                int dr = a.row + drow[i];
                int dc = a.col + dcol[i];

                if(dr<0||dc<0||dr>=N||dc>=N)
                    continue;

                if(sub[dr][dc] == 0){
                    sub[dr][dc] = 3;
                    q.offer(new rowCol(dr, dc, a.cnt+1));
                }

                else if(sub[dr][dc] == 1){
                    sub[dr][dc] = 4;
                    q.offer(new rowCol(dr, dc, a.cnt+1));

                    sum += a.cnt+1;
                }
            }
        }

        min = Math.min(min, sum);
    }
}

class rowCol{
    int row, col, cnt;

    rowCol(int row, int col, int cnt){
        this.row = row;
        this.col = col;
        this.cnt = cnt;
    }
}