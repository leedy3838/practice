package baekjoon_14499;

import java.io.*;
import java.util.*;

public class Main {

    private static final StringBuilder sb = new StringBuilder();

    private static final int[] dX = new int[]{0, 0, -1, 1}; //동, 서, 북, 남
    private static final int[] dY = new int[]{1, -1, 0, 0};

    private static int N, M, x, y, K;
    private static int[][] map;
    private static final List<Integer> oper = new ArrayList<>();
    private static final int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            oper.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static void solveProblem() {
        for (int i = 0; i < K; i++) {
            int operation = oper.get(i);
            int move = move(operation);

            if (move != -1) {
                sb.append(move).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static int move(int direction) {
        int dx = x + dX[direction - 1];
        int dy = y + dY[direction - 1];

        if (dx < 0 || dy < 0 || dx >= N || dy >= M) {
            return -1;
        }

        int tmp = dice[1];
        switch (direction) {
            case 1: //동
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
                break;
            case 2: //서
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = tmp;
                break;
            case 3: //북
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
                break;
            case 4: //남
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
                break;
        }

        x = dx;
        y = dy;


        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }

        return dice[1];
    }
}
