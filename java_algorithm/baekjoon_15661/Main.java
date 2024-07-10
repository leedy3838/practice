package baekjoon_15661;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, answer = Integer.MAX_VALUE;
    private static int[][] data;
    private static boolean[] isStartTeam;   //start 팀인지 확인

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        isStartTeam = new boolean[N];
        data = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solveProblem() {
        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int count) {
        if (count == N) {
            int sumStart = 0;
            int sumLink = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (isStartTeam[i] == isStartTeam[j]) {
                        if (isStartTeam[i]) {               //둘 다 start 팀인 경우
                            sumStart += data[i][j] + data[j][i];
                        } else {                            //둘 다 link 팀인 경우
                            sumLink += data[i][j] + data[j][i];
                        }
                    }
                }
            }

            answer = Math.min(answer, Math.abs(sumStart - sumLink));
        } else {
            isStartTeam[count] = true;
            dfs(count + 1);

            isStartTeam[count] = false;
            dfs(count + 1);
        }
    }
}
