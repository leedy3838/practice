package baekjoon_17281;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[][] data;

    private static final int[] order = new int[10];

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        data = new int[N + 1][10];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= 9; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solveProblem() {
        order[4] = 1;
        permutation(2);

        System.out.println(ans);
    }

    private static void permutation(int idx) {
        if (idx > 9) {
            playGame();
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (order[i] > 0)
                continue;

            order[i] = idx;
            permutation(idx + 1);
            order[i] = 0;
        }
    }

    private static void playGame() {
        int score = 0, exIdx = 0;

        for (int loop = 1; loop <= N; loop++) {
            boolean[] base = new boolean[4];
            int outCnt = 0;

            while (outCnt < 3) {
                int nowIdx = (exIdx + 1) % 9;

                if (nowIdx == 0) {
                    nowIdx = 9;
                }

                int nowAttack = order[nowIdx];

                switch (data[loop][nowAttack]) {
                    case 0:
                        outCnt++;
                        break;
                    case 1:
                        for (int i = 3; i > 0; i--) {
                            if (base[i]) {
                                if (i + 1 > 3) {
                                    score++;
                                } else {
                                    base[i + 1] = true;
                                }
                                base[i] = false;
                            }
                        }
                        base[1] = true;
                        break;
                    case 2:
                        for (int i = 3; i > 0; i--) {
                            if (base[i]) {
                                if (i + 2 > 3) {
                                    score++;
                                } else {
                                    base[i + 2] = true;
                                }
                                base[i] = false;
                            }
                        }
                        base[2] = true;
                        break;
                    case 3:
                        for (int i = 3; i > 0; i--) {
                            if (base[i]) {
                                score++;
                                base[i] = false;
                            }
                        }
                        base[3] = true;
                        break;
                    case 4:
                        for (int i = 3; i > 0; i--) {
                            if (base[i]) {
                                score++;
                                base[i] = false;
                            }
                        }
                        score++;
                        break;
                }

                exIdx = nowIdx;
            }
        }

        ans = Math.max(ans, score);
    }
}
