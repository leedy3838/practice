package baekjoon_15665;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final StringBuilder sb = new StringBuilder();

    private static int M;
    private static int[] order;
    private static List<Integer> data = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        order = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            data.add(Integer.parseInt(st.nextToken()));
        }

        data = data.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private static void solveProblem() {
        backTracking(0);

        System.out.println(sb);
    }

    private static void backTracking(int count) {

        if (count == M) {
            for (int i = 0; i < M; i++)
                sb.append(order[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i : data) {
            order[count] = i;
            backTracking(count + 1);
        }
    }
}