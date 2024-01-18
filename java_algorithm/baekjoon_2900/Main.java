package baekjoon_2900;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        solveProblem();
    }

    private static void solveProblem() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        long[] sumArray = new long[N];

        //인자 값 X의 숫자별로 몇 번씩 들어왔는지 저장
        Map<Integer, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int X = Integer.parseInt(st.nextToken());

            if (map.containsKey(X)) {
                int value = map.get(X);
                map.put(X, value + 1);
            } else {
                map.put(X, 1);
            }
        }

        //array에 something을 한 결과를 저장
        Set<Integer> keySet = map.keySet();
        for (int jump : keySet) {
            int jumpCnt = map.get(jump);
            something(jump, N, jumpCnt, array);
        }

        sumArray[0] = array[0];
        for (int i = 1; i < N; i++) {
            sumArray[i] = sumArray[i - 1] + array[i];
        }

        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            if (L == 0)
                sb.append(sumArray[R]).append("\n");
            else
                sb.append(sumArray[R] - sumArray[L - 1]).append("\n");
        }

        System.out.println(sb);
    }

    private static void something(int jump, int N, int cnt, int[] array) {
        int i = 0;
        while (i < N) {
            array[i] = array[i] + cnt;
            i = i + jump;
        }
    }
}
