package baekjoon_21318;

import java.io.*;
import java.util.*;

public class Main {

    private static final List<Integer> prefixSum = new ArrayList<>();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws Exception {
        int n = Integer.parseInt(br.readLine());
        List<Integer> valList = new ArrayList<>();
        valList.add(0);                 //0번 idx
        prefixSum.add(0);               //0번 idx

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            valList.add(num);

            if (valList.get(i - 1) > num) {
                prefixSum.add(prefixSum.get(i - 1) + 1);
            } else {
                prefixSum.add(prefixSum.get(i - 1));
            }
        }
    }

    private static void solveProblem() throws Exception {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(prefixSum.get(end) - prefixSum.get(start)).append("\n");
        }

        System.out.print(sb);
    }
}
