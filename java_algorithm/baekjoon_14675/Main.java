package baekjoon_14675;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void solveProblem() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] edgeNum = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            edgeNum[num1]++;
            edgeNum[num2]++;
        }

        int q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (type == 1) {
                if (edgeNum[num] > 1) {
                    sb.append("yes\n");
                } else {
                    sb.append("no\n");
                }
            } else {
                sb.append("yes\n");
            }
        }

        System.out.println(sb);
    }
}
