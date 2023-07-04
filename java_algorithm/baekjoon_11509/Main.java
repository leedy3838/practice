package baekjoon_11509;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] arrowHeight = new int[1_000_002];

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int cnt = 0, height = 0;
        for (int i = 0; i < N; i++) {
            height = arr[i];

            if (arrowHeight[height + 1] == 0) {
                arrowHeight[height]++;
                cnt++;
            }

            else {
                arrowHeight[height + 1]--;
                arrowHeight[height]++;
            }
        }

        System.out.println(cnt);
    }
}