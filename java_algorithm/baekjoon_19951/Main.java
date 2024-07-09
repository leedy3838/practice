package baekjoon_19951;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void solveProblem() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] map = new int[N + 1];
        int[] prefixSum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int startIdx = Integer.parseInt(st.nextToken());
            int endIdx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            prefixSum[startIdx] += val;
            if (endIdx < N)
                prefixSum[endIdx + 1] -= val;
        }

        int addVal = 0;
        for (int i = 1; i <= N; i++) {
            addVal += prefixSum[i];
            map[i] += addVal;
        }

        for (int i = 1; i <= N; i++) {
            sb.append(map[i]).append(" ");
        }

        System.out.println(sb);
    }
}
