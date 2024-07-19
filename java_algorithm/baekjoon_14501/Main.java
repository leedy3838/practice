package baekjoon_14501;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static Node[] job;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        job = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            job[i] = new Node(time, val);
        }
    }

    private static void solveProblem() {
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            Node todayJob = job[i];

            if (i + todayJob.time <= N) {
                dp[i + todayJob.time] = Math.max(dp[i + todayJob.time], dp[i] + todayJob.val);
            }

            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        System.out.println(dp[N]);
    }

    private static class Node {
        int time, val;

        public Node(int time, int val) {
            this.time = time;
            this.val = val;
        }
    }
}
