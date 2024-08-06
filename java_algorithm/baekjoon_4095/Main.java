package baekjoon_4095;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();

    private static int N, M;
    private static int[][] prefixSum;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        StringTokenizer st;

        prefixSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                int num = Integer.parseInt(st.nextToken());
                prefixSum[i][j] = prefixSum[i][j - 1] + prefixSum[i - 1][j] - prefixSum[i - 1][j - 1] + num;
            }
        }
    }

    private static void solveProblem() throws IOException {

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            firstSetting();
            sb.append(squareCnt()).append("\n");
        }

        System.out.println(sb);
    }

    private static int squareCnt() {
        int maxSquareLen = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                //최대 정사각형 크기 갱신 불가
                if (prefixSum[i][j] <= maxSquareLen * maxSquareLen)
                    continue;

                //이전 최대 정사각형 크기 + 1 부터 확인
                int loop = maxSquareLen + 1;
                while (i - loop >= 0 && j - loop >= 0) {
                    int squareV = prefixSum[i][j] - prefixSum[i - loop][j] - prefixSum[i][j - loop] + prefixSum[i - loop][j - loop];
                    if (squareV == loop * loop) {
                        maxSquareLen = loop;
                    }
                    loop++;
                }
            }
        }

        return maxSquareLen;
    }
}
