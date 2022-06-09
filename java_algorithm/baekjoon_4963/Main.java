package baekjoon_4963;

import java.io.*;
import java.util.*;

public class Main {
    static int row, col;
    static int[][] rowCol;
    static boolean[][] visited;
    static int[] dr = {1, 1, 1, -1, -1, -1, 0, 0};
    static int[] dc = {-1, 0, 1, -1, 0, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        while (true) {
            int cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());

            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

            if (col == 0 && row == 0)
                break;

            rowCol = new int[row][col];
            visited = new boolean[row][col];

            for(int i = 0; i<row; i++){
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j<col; j++)
                    rowCol[i][j] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i<row; i++)
                for(int j = 0; j<col; j++)
                    if(rowCol[i][j] == 1 && !visited[i][j]){
                        search(i, j);
                        cnt++;
                    }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }

    static void search(int r, int c){
        visited[r][c] = true;

        for(int i = 0; i<8; i++){
            int dR = r + dr[i];
            int dC = c + dc[i];

            if(dR<0||dC<0||dR>=row||dC>=col)
                continue;

            if(rowCol[dR][dC] == 1 && !visited[dR][dC])
                search(dR, dC);
        }
    }
}