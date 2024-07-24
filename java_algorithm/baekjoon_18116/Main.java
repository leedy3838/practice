package baekjoon_18116;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] parent;
    private static int[] cnt;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        N = Integer.parseInt(br.readLine());

        parent = new int[1_000_001];
        cnt = new int[1_000_001];

        for (int i = 1; i <= 1_000_000; i++) {
            parent[i] = i;
            cnt[i] = 1;
        }
    }

    private static void solveProblem() throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String op = st.nextToken();
            if (op.equals("I")) {
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());

                int small = Math.min(num1, num2);
                int big = Math.max(num1, num2);

                union(small, big);
            } else {
                int num = Integer.parseInt(st.nextToken());

                sb.append(cnt[find(num)]).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static int find(int num) {
        if (parent[num] == num) {
            return num;
        } else {
            return parent[num] = find(parent[num]);
        }
    }

    private static void union(int num1, int num2) {
        int findNum1 = find(num1);
        int findNum2 = find(num2);

        if (findNum1 != findNum2) {
            cnt[findNum1] += cnt[findNum2];
            parent[findNum2] = findNum1;
        }
    }
}
