package baekjoon_2157;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static final List<List<Node>> data = new ArrayList<>();
    private static int[][] dp;  // row - cityCnt, col - city, val - val

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //총 도시 수
        M = Integer.parseInt(st.nextToken());   //갈 수 있는 도시 수
        int k = Integer.parseInt(st.nextToken());   //항공로의 수

        for (int i = 0; i <= N; i++) {
            data.add(new ArrayList<>());
        }
        dp = new int[M + 1][N + 1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            if (from < to) {
                data.get(from).add(new Node(to, val, 0));
            }
        }
    }

    private static void solveProblem() {

        for (int cityCnt = 1; cityCnt < M; cityCnt++) {
            for (int city = 1; city <= N; city++) {
                if (city != 1 && dp[cityCnt][city] == 0) {
                    continue;
                }

                for (Node next : data.get(city)) {
                    if (dp[cityCnt + 1][next.index] < dp[cityCnt][city] + next.val) {
                        dp[cityCnt + 1][next.index] = dp[cityCnt][city] + next.val;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= M; i++) {
            ans = Math.max(ans, dp[i][N]);
        }

        System.out.println(ans);
    }

    private static class Node {
        int index, val, cityCnt;

        public Node(int index, int val, int cityCnt) {
            this.index = index;
            this.val = val;
            this.cityCnt = cityCnt;
        }
    }
}
