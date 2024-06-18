package baekjoon_14284;

import java.io.*;
import java.util.*;

public class Main {

    private static final int MAX_VAL = 500_000_001;

    private static int N;
    private static int targetFrom, targetTo;
    private static final List<List<Node>> data = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            data.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            data.get(from).add(new Node(to, weight));
            data.get(to).add(new Node(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        targetFrom = Integer.parseInt(st.nextToken());
        targetTo = Integer.parseInt(st.nextToken());
    }

    private static void solveProblem() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, MAX_VAL);
        dist[targetFrom] = 0;

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(targetFrom, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (Node nextV : data.get(node.to)) {
                if (dist[nextV.to] > dist[node.to] + nextV.weight) {
                    dist[nextV.to] = dist[node.to] + nextV.weight;
                    pq.add(nextV);
                }
            }
        }

        System.out.println(dist[targetTo]);
    }

    static class Node implements Comparable<Node>{
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
}
