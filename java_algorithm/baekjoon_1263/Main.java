package baekjoon_1263;

import java.io.*;
import java.util.*;

public class Main {

    private static final Queue<Node> data = new PriorityQueue<>();
    private static final boolean[] isWorking = new boolean[1_000_001];

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int cost = Integer.parseInt(st.nextToken());
                int endTime = Integer.parseInt(st.nextToken());

                data.offer(new Node(cost, endTime));
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        while (!data.isEmpty()) {
            if (!work(data.poll())) {
                System.out.println(-1);
                return;
            }
        }

        for (int i = 1; i <= 1_000_000; i++) {
            if (isWorking[i]) {
                System.out.println(i);
                return;
            }
        }
    }

    private static boolean work(Node node) {

        while (node.cost > 0) {
            if (node.endTime == 0)
                return false;

            if (!isWorking[node.endTime - 1]) {
                node.cost--;
                isWorking[node.endTime - 1] = true;
            }

            node.endTime--;
        }

        return true;
    }

    private static class Node implements Comparable<Node> {
        int cost, endTime;

        public Node(int cost, int endTime) {
            this.cost = cost;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Node node) {
            return this.endTime - node.endTime;
        }
    }
}
