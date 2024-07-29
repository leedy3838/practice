package baekjoon_20159;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] oddDataSum;
    private static int[] evenDataSum;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        oddDataSum = new int[N / 2 + 1];
        evenDataSum = new int[N / 2 + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N / 2; i++) {
            oddDataSum[i] = oddDataSum[i - 1] + Integer.parseInt(st.nextToken());
            evenDataSum[i] = evenDataSum[i - 1] + Integer.parseInt(st.nextToken());
        }
    }

    private static void solveProblem() {
        int maxVal = 0;

        for (int i = 0; i < N; i++) {
            if (i % 2 == 1) {   //내 차례에서 밑장 빼기
                maxVal = Math.max(maxVal, oddDataSum[i / 2] + evenDataSum[N / 2] - evenDataSum[i / 2]);
            } else {            //상대 차례에서 밑장 빼기
                maxVal = Math.max(maxVal, oddDataSum[i / 2 + 1] + evenDataSum[N / 2 - 1] - evenDataSum[i / 2]);
            }
        }

        System.out.println(maxVal);
    }
}
