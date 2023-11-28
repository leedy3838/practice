package baekjoon_18352;

import java.io.*;
import java.util.*;

public class Main {

    private static final List<List<Integer>> edgeList = new ArrayList<>();
    private static int N, K, X;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            for(int i = 0; i <= N; i++) {
                edgeList.add(new ArrayList<>());
            }

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                edgeList.get(from).add(to);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int[] visitCnt = new int[N + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(X);

        while (!q.isEmpty()) {
            int node = q.poll();

            for (Integer nextV : edgeList.get(node)) {

                if(nextV == X)
                    continue;
                if(visitCnt[nextV] != 0)
                    continue;

                q.offer(nextV);
                visitCnt[nextV] = visitCnt[node] + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean isNone = true;

        for(int i = 1; i <= N; i++) {
            if(visitCnt[i] == K){
                isNone = false;
                sb.append(i).append("\n");
            }
        }

        if(isNone)
            System.out.println(-1);
        else
            System.out.print(sb);
    }
}
