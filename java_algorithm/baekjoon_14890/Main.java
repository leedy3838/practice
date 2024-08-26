package baekjoon_14890;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, L;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solveProblem() {
        int ans = 0;

        //가로 방향 확인
        for (int row = 0; row < N; row++) {
            if (calRow(row)) {
                ans++;
            }
        }

        //세로 방향 확인
        for (int col = 0; col < N; col++) {
            if (calCol(col)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static boolean calRow(int row) {
        boolean[] isSlide = new boolean[N];

        for (int col = 0; col < N - 1; col++) {
            int diff = map[row][col] - map[row][col + 1];

            if (diff > 1 || diff < -1) {
                return false;
            } else if (diff == -1) {    //현재 위치가 더 낮음
                for (int j = 0; j < L; j++) { // 올라가는 경사로를 설치할 수 있는지 확인
                    if (col - j < 0 || isSlide[col - j]) return false;
                    if (map[row][col] != map[row][col - j]) return false;
                    isSlide[col - j] = true; //경사면 설치
                }
            } else if (diff == 1) {     //현재 위치가 더 높음
                for (int j = 1; j <= L; j++) { //내려가는 경사로를 설치할 수 있는지 확인
                    if (col + j >= N || isSlide[col + j]) return false;
                    if (map[row][col] - 1 != map[row][col + j]) return false;
                    isSlide[col + j] = true; //경사면 설치
                }
            }

        }
        return true;

    }

    private static boolean calCol(int col) {
        boolean[] isSlide = new boolean[N];

        for (int row = 0; row < N - 1; row++) {
            int diff = map[row][col] - map[row + 1][col];

            if (diff > 1 || diff < -1) {
                return false;
            } else if (diff == -1) {    //현재 위치가 더 낮음
                for (int j = 0; j < L; j++) { // 올라가는 경사로를 설치할 수 있는지 확인
                    if (row - j < 0 || isSlide[row - j]) return false;
                    if (map[row][col] != map[row - j][col]) return false;
                    isSlide[row - j] = true; //경사면 설치
                }
            } else if (diff == 1) {     //현재 위치가 더 높음
                for (int j = 1; j <= L; j++) { //내려가는 경사로를 설치할 수 있는지 확인
                    if (row + j >= N || isSlide[row + j]) return false;
                    if (map[row][col] - 1 != map[row + j][col]) return false;
                    isSlide[row + j] = true; //경사면 설치
                }
            }
        }

        return true;
    }
}
