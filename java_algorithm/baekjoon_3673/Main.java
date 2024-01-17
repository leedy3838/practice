package baekjoon_3673;

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

        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[] mod = new int[d];

            int sum = 0;
            int count = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                sum += Integer.parseInt(st.nextToken());
                sum %= d;

                count += mod[sum];
                mod[sum]++;
            }
            //나머지가 0인 경우는 위의 for문과 별도로 해당 index까지의 누적합 자체만으로 조건 충족이므로 더해줌
            count += mod[0];

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
