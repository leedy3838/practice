package baekjoon_20055;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, K;
    private static int[] belt;
    private static boolean[] isRobot;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[N * 2 + 1];
        isRobot = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N * 2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solveProblem() {
        int ans = 0;
        int okCnt = N;

        while (okCnt > N - K) {
            rotate();
            okCnt -= moveRobot();
            okCnt -= stateRobot();

            ans++;
        }

        System.out.println(ans);
    }

    private static void rotate() {
        int tmp = belt[N * 2];

        for (int i = N * 2; i >= 1; i--) {
            belt[i] = belt[i - 1];
        }
        for (int i = N; i >= 1; i--) {
            if (isRobot[i - 1]) {
                isRobot[i] = true;
                isRobot[i - 1] = false;
            }
        }

        if (isRobot[N]) {
            isRobot[N] = false;
        }

        belt[1] = tmp;
    }

    private static int moveRobot() {
        int becomeZeroCnt = 0;

        for (int i = N - 1; i >= 1; i--) {
            if (isRobot[i]) {
                if (!isRobot[i + 1] && belt[i + 1] > 0) {
                    isRobot[i + 1] = true;
                    belt[i + 1]--;
                    isRobot[i] = false;

                    if (belt[i + 1] == 0) {
                        becomeZeroCnt++;
                    }
                }
            }
        }

        if (isRobot[N]) {
            isRobot[N] = false;
        }

        return becomeZeroCnt;
    }

    private static int stateRobot() {
        if (belt[1] != 0) {
            isRobot[1] = true;
            belt[1]--;

            if (belt[1] == 0) {
                return 1;
            }
        }

        return 0;
    }
}
