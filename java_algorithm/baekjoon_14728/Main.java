package baekjoon_14728;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, T;
    private static Node[] data;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        data = new Node[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            data[i] = new Node(K, S);
        }
    }

    private static void solveProblem() {
        int[] dp = new int[T + 1];

        for (int index = 1; index <= N; index++) {
            for (int nowTime = T; nowTime - data[index].time >= 0; nowTime--) {
                dp[nowTime] = Math.max(dp[nowTime], dp[nowTime - data[index].time] + data[index].score);
            }
        }

        System.out.println(dp[T]);
    }

    private static class Node {
        int time, score;

        public Node(int time, int score) {
            this.time = time;
            this.score = score;
        }
    }
}
