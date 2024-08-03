package baekjoon_16206;

import java.io.*;
import java.util.*;

public class Main {

    private static int M, ans = 0;
    private static final Queue<Integer> data = new PriorityQueue<>((o1, o2) -> {
        if (o1 % 10 == 0 && o2 % 10 != 0) {
            return -1;
        } else if (o1 % 10 != 0 && o2 % 10 == 0) {
            return 1;
        } else {
            return Integer.compare(o1, o2);
        }
    });

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 10) {
                ans++;
            } else if (num > 10){
                data.offer(num);
            }
        }
    }

    private static void solveProblem() {
        while (!data.isEmpty() && M > 0) {
            int num = data.poll();

            ans++;
            M--;

            if (num - 10 == 10) {
                ans++;
            } else if (num - 10 > 10) {
                data.add(num - 10);
            }
        }

        System.out.println(ans);
    }
}
