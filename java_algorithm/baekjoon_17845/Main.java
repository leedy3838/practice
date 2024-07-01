package baekjoon_17845;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, K;
    private static Node[] data;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //최대 공부 시간
        K = Integer.parseInt(st.nextToken());   //과목 수

        data = new Node[K + 1];

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            data[i] = new Node(weight, time);
        }
    }

    private static void solveProblem() {
        long[][] dp = new long[K + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j >= data[i].time) {
                    dp[i][j] = Math.max(dp[i][j - 1], Math.max(dp[i - 1][j], dp[i - 1][j - data[i].time] + data[i].weight));
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        System.out.println(dp[K][N]);
    }

    private static class Node {
        int weight, time;

        public Node(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
}
