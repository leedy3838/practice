package baekjoon_10819;

import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int[] arr;
    private static int[] result;
    private static int maxVal = Integer.MIN_VALUE;
    private static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        isVisit = new boolean[n];
        result = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(maxVal);
    }

    static void dfs(int depth) {
        if (depth == n) {
            int sum = 0;

            for (int i = 0; i < n - 1; i++) {
                sum += Math.abs(result[i] - result[i + 1]);
            }

            maxVal = Math.max(sum, maxVal);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isVisit[i]) {
                isVisit[i] = true;
                result[depth] = arr[i];

                dfs(depth + 1);

                isVisit[i] = false;
            }
        }
    }
}
