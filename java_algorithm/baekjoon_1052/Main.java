package baekjoon_1052;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, K;

    public static void main(String[] args) throws IOException {
        fistSetting();
        solveProblem();
    }

    private static void fistSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    private static void solveProblem() {
        int ans = 0;

        while (Integer.bitCount(N) > K) {
            ans++;
            N++;
        }

        System.out.println(ans);
    }
}
