package baekjoon_17144;

import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int R, C;
    static int[] dRow = {1, -1, 0, 0};
    static int[] dCol = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int cleanR = -1;

        for(int i = 0; i<R; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<C; j++) {
                int a = Integer.parseInt(st.nextToken());
                map[i][j] = a;

                if(a == -1 && cleanR == -1)
                    cleanR = i;
            }
        }

        while(T-->0){
            spread();
            move(cleanR);
        }

        int sum = 0;
        for(int i = 0; i<R; i++)
            for (int j = 0; j < C; j++)
                sum += map[i][j];

        System.out.println(sum+2);
    }

    //미세먼지 확산
    static void spread(){
        List<Data> l = new ArrayList<>();

        for(int i = 0; i<R; i++)
            for(int j = 0; j<C; j++)
                if(map[i][j]>=5)
                    l.add(new Data(i, j, map[i][j]));

        for (Data data : l) {
            int count = 0;

            int r = data.row;
            int c = data.col;
            int v = data.val;

            for (int j = 0; j < 4; j++) {
                int dr = r + dRow[j];
                int dc = c + dCol[j];

                if (dr < 0 || dc < 0 || dr >= R || dc >= C)
                    continue;
                if (map[dr][dc] == -1)
                    continue;

                map[dr][dc] += v / 5;
                count++;
            }

            map[r][c] -= v / 5 * count;
        }
    }

    //공기청정기 가동
    static void move(int cleanR){
        for(int i = cleanR-2; i>=0; i--)
            map[i+1][0] = map[i][0];
        for(int i = 0; i<C-1; i++)
            map[0][i] = map[0][i+1];
        for(int i = 0; i<cleanR; i++)
            map[i][C-1] = map[i+1][C-1];
        for(int i = C-1; i>1; i--)
            map[cleanR][i] = map[cleanR][i-1];
        map[cleanR][1] = 0;

        for(int i = cleanR+2; i<R-1; i++)
            map[i][0] = map[i+1][0];
        for(int i = 0; i<C-1; i++)
            map[R-1][i] = map[R-1][i+1];
        for(int i = R-1; i>cleanR+1; i--)
            map[i][C-1] = map[i-1][C-1];
        for(int i = C-1; i>1; i--)
            map[cleanR+1][i] = map[cleanR+1][i-1];
        map[cleanR+1][1] = 0;
    }
}

class Data{
    int row, col, val;

    Data(int row, int col, int val){
        this.row = row;
        this.col = col;
        this.val = val;
    }
}