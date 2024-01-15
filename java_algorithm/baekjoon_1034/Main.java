package baekjoon_1034;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, M, K;
    private static Boolean[][] map;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new Boolean[N][];

            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split("\\s*"))
                        .mapToInt(Integer::parseInt)
                        .mapToObj(num -> num == 1)
                        .toArray(Boolean[]::new);
            }

            K = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int max = 0;
        boolean[] isCheck = new boolean[N];

        for (int i = 0; i < N; i++) {

            //이미 확인한 줄
            if (isCheck[i])
                continue;

            int lightOffCnt = countLightOffNum(i);
            //불을 전부 끌 수 없는 상황
            if (K < lightOffCnt || K % 2 != lightOffCnt % 2)
                continue;

            int sameCnt = 1;
            for (int j = i + 1; j < N; j++) {
                boolean isSame = true;

                //행의 정보가 같은지
                for (int k = 0; k < M; k++) {
                    if (map[i][k] != map[j][k]) {
                        isSame = false;
                        break;
                    }
                }

                if (isSame) {
                    sameCnt++;
                    isCheck[j] = true;
                }
            }

            max = Math.max(max, sameCnt);
        }

        System.out.println(max);
    }

    private static int countLightOffNum(int row) {
        int lightOffNum = 0;

        for (int i = 0; i < M; i++) {
            if (!map[row][i])
                lightOffNum++;
        }

        return lightOffNum;
    }
}
