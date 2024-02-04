package baekjoon_15970;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static final List<List<Integer>> l = new ArrayList<>();

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            for (int i = 0; i <= N; i++) {
                l.add(new ArrayList<>());
            }

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int point = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());

                l.get(color).add(point);
            }

            for (int i = 0; i <= N; i++) {
                Collections.sort(l.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int ans = 0;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < l.get(i).size(); j++) {
                if (j == 0) { // 처음
                    ans += l.get(i).get(j + 1) - l.get(i).get(j);
                } else if (j == l.get(i).size() - 1) { // 끝
                    ans += l.get(i).get(j) - l.get(i).get(j - 1);
                } else { // 중간
                    int t = l.get(i).get(j + 1) - l.get(i).get(j);
                    int f = l.get(i).get(j) - l.get(i).get(j - 1);

                    ans += Math.min(t, f);
                }
            }
        }

        System.out.println(ans);
    }
}
