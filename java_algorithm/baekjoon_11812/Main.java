package baekjoon_11812;

import java.io.*;
import java.util.*;

public class Main {

    static long N;
    static int K;
    static long[][] nodeList;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Long.parseLong(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            nodeList = new long[Q][2];
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());

                long A = Long.parseLong(st.nextToken());
                long B = Long.parseLong(st.nextToken());

                nodeList[i][0] = A;
                nodeList[i][1] = B;
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        StringBuilder sb = new StringBuilder();

        for (long[] node : nodeList) {
            long A = node[0];
            long B = node[1];

            //편향 트리의 경우
            if (K == 1) {
                sb.append(Math.abs(A - B)).append("\n");
                continue;
            }

            long ARank = getRank(A);
            long BRank = getRank(B);

            if (ARank == 0 || BRank == 0) {
                sb.append(ARank + BRank).append("\n");
                continue;
            }

            int dist = 0;
            while (ARank > BRank) {
                A = getParent(A);
                ARank = getRank(A);
                dist++;
            }

            while (BRank > ARank) {
                B = getParent(B);
                BRank = getRank(B);
                dist++;
            }

            while (A != B) {
                A = getParent(A);
                B = getParent(B);
                dist += 2;
            }

            sb.append(dist).append("\n");
        }

        System.out.print(sb);
    }

    static long getRank(long nodeNum) {
        if (nodeNum == 1)
            return 0;

        long lineMaxNum = 1;
        long h = 0;

        do {
            lineMaxNum += (long) Math.pow(K, ++h);
        } while (nodeNum > lineMaxNum);

        return h;
    }

    static long getParent(long nodeNum) {
        return (nodeNum - 2) / K + 1;
    }
}
