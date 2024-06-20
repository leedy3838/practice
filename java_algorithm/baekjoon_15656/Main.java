package baekjoon_15656;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[] dataArr, printArr;

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dataArr = Arrays.stream(br.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        printArr = new int[M];
    }

    private static void solveProblem() {
        dfs(0);
        System.out.print(sb);
    }

    private static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(printArr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            printArr[depth] = dataArr[i];
            dfs(depth + 1);
        }
    }
}
