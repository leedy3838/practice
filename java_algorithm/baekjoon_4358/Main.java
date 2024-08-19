package baekjoon_4358;

import java.io.*;
import java.util.*;

public class Main {

    private static final Map<String, Integer> map = new TreeMap<>();
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while ((str = br.readLine()) != null && !str.isEmpty()) {
            map.put(str, map.getOrDefault(str, 0) + 1);
            cnt++;
        }
    }

    private static void solveProblem() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            // 소수점 4자리까지 포맷팅
            double percentage = (double) value / cnt * 100;
            sb.append(key).append(" ").append(String.format("%.4f", percentage)).append("\n");
        }

        System.out.print(sb);
    }
}
