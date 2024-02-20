package baekjoon_11657;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static final List<Node> list = new ArrayList<>();
    private static long[] dist;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            dist = new long[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                list.add(new Node(from, to, dist));
            }

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        StringBuilder sb = new StringBuilder();

        if (bellmanFord()) {
            for (int i = 2; i <= N; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    sb.append(dist[i]).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
            }
        } else {
            sb.append(-1).append("\n");
        }

        System.out.print(sb);
    }

    private static boolean bellmanFord() {

        dist[1] = 0;

        // N - 1번 loop
        for (int i = 1; i <= N; i++) {
            for (Node node : list) {
                if (dist[node.from] != Integer.MAX_VALUE && dist[node.to] > dist[node.from] + node.value) {
                    dist[node.to] = dist[node.from] + node.value;

                    //음수 그래프 탐지
                    //N번째 순회에서 간선 완화가 일어나면 음수 사이클 존재
                    if (i == N) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static class Node {
        int from, to, value;

        public Node(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}
