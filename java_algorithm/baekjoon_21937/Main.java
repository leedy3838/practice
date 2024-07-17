package baekjoon_21937;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int target;
    private static final List<List<Integer>> parentIdx = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //작업할 개수
        int M = Integer.parseInt(st.nextToken());   //작업 순서 정보의 개수

        for (int i = 0; i <= N; i++)
            parentIdx.add(new ArrayList<>());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int preJob = Integer.parseInt(st.nextToken());
            int afterJob = Integer.parseInt(st.nextToken());

            parentIdx.get(afterJob).add(preJob);
        }

        target = Integer.parseInt(br.readLine());
    }

    private static void solveProblem() {
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisit = new boolean[N + 1];

        for (int num : parentIdx.get(target)) {
            count++;

            q.add(num);
            isVisit[num] = true;
        }

        while (!q.isEmpty()) {
            int num = q.poll();

            for (int parentV : parentIdx.get(num)) {
                if (isVisit[parentV])
                    continue;

                count++;

                q.add(parentV);
                isVisit[parentV] = true;
            }
        }

        System.out.println(count);
    }
}
