package baekjoon_7490;

import java.io.*;
import java.util.*;

public class Main {

    private static List<String> list;
    private static int N;

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void solveProblem() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();

            backTracking(1, "1");

            Collections.sort(list);

            for (String str : list)
                sb.append(str).append("\n");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void backTracking(int num, String s) {
        if (num == N) {
            String str = s.replaceAll(" ", "");

            if (operation(str)) {
                list.add(s);
            }
        } else {
            backTracking(num + 1, s.concat("+").concat(String.valueOf(num + 1)));
            backTracking(num + 1, s.concat("-").concat(String.valueOf(num + 1)));
            backTracking(num + 1, s.concat(" ").concat(String.valueOf(num + 1)));
        }
    }

    private static boolean operation(String str) {
        StringTokenizer st = new StringTokenizer(str, "+|-", true);
        int sum = Integer.parseInt(st.nextToken());

        while (st.hasMoreTokens()) {
            String op = st.nextToken();

            if (op.equals("+")) {
                sum += Integer.parseInt(st.nextToken());
            } else {
                sum -= Integer.parseInt(st.nextToken());
            }
        }

        return sum == 0;
    }
}
