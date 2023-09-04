package baekjoon_16724;

import java.io.*;
import java.util.*;

public class Main {

    //상, 하, 좌, 우
    static int[] dR = {-1, 1, 0, 0};
    static int[] dC = {0, 0, -1, 1};
    static int N, M;
    static char[][] map;
    static boolean[][] isVisit;
    public static void main(String[] args) throws IOException{

        firstSetting();
        System.out.println(findSafeZone());
    }

    private static void firstSetting() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        isVisit = new boolean[N][M];

        for(int i = 0; i < N; i++){
            String input = br.readLine();

            for(int j = 0; j < M; j++)
                map[i][j] = input.charAt(j);
        }
    }

    private static int findSafeZone(){

        int cnt = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(isVisit[i][j])
                    continue;

                cnt++;
                searchZone(i, j);
            }
        }

        return cnt;
    }

    //각각의 좌표에 해당하는 map의 좌표 확인
    private static void searchZone(int row, int col){

        isVisit[row][col] = true;

        //상 하 좌 우 순서
        for(int i = 0; i < 4; i++){
            int dr = row + dR[i];
            int dc = col + dC[i];

            if(dr < 0 || dc < 0 || dr >= N || dc >= M)
                continue;
            if(isVisit[dr][dc])
                continue;

            //상
            if(i == 0){
                if(map[dr][dc] == 'D')
                    searchZone(dr, dc);
                if(map[row][col] == 'U')
                    searchZone(dr, dc);
            }
            //하
            else if(i == 1){
                if(map[dr][dc] == 'U')
                    searchZone(dr, dc);
                if(map[row][col] == 'D')
                    searchZone(dr, dc);
            }
            //좌
            else if(i == 2){
                if(map[dr][dc] == 'R')
                    searchZone(dr, dc);
                if(map[row][col] == 'L')
                    searchZone(dr, dc);
            }
            //우
            else {
                if(map[dr][dc] == 'L')
                    searchZone(dr, dc);
                if(map[row][col] == 'R')
                    searchZone(row, col + 1);
            }
        }
    }
}
