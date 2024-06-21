package baekjoon_20364;

import java.io.*;
import java.util.*;

public class Main {

    //true -> 방문한 적 있음
    private static boolean[] map;
    private static final List<Integer> want = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        map = new boolean[N + 1];

        while (Q-- > 0) {
            want.add(Integer.parseInt(br.readLine()));
        }
    }

    private static void solveProblem() {
        StringBuilder sb = new StringBuilder();

        for (int wantLand : want) {
            List<Integer> conflict = new LinkedList<>();
            int temp = wantLand;

            while (temp != 1) {
                if (map[temp]) {
                    conflict.add(temp);
                }

                if (temp % 2 == 0) {
                    temp /= 2;
                } else {
                    temp = (temp - 1) / 2;
                }
            }

            if (conflict.isEmpty()) {
                sb.append(0).append("\n");
                map[wantLand] = true;
            } else {
                sb.append(conflict.get(conflict.size() - 1)).append("\n");
            }
        }

        System.out.print(sb);
    }
}
