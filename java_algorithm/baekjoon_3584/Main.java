package baekjoon_3584;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int findA;
    private static int findB;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void solveProblem() throws IOException {

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            firstSetting();

            int rankA = getRank(findA);
            int rankB = getRank(findB);

            while (rankA > rankB) {
                findA = parent[findA];
                rankA--;
            }
            while (rankB > rankA) {
                findB = parent[findB];
                rankB--;
            }

            while (findA != findB) {
                findA = parent[findA];
                findB = parent[findB];
            }

            sb.append(findA).append("\n");
        }

        System.out.println(sb);
    }

    private static void firstSetting() throws IOException {

        int N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int parentV = Integer.parseInt(st.nextToken());
            int childV = Integer.parseInt(st.nextToken());

            parent[childV] = parentV;
        }

        st = new StringTokenizer(br.readLine());
        findA = Integer.parseInt(st.nextToken());
        findB = Integer.parseInt(st.nextToken());
    }

    private static int getRank(int val) {
        int rank = 0;

        while (parent[val] != 0) {
            rank++;
            val = parent[val];
        }

        return rank;
    }
}
