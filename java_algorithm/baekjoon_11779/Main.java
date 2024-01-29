package baekjoon_11779;

import java.io.*;
import java.util.*;

public class Main {

    private static int n, startV, targetV;
    private static final List<List<Node>> data = new ArrayList<>();

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            for (int i = 0; i <= n; i++)
                data.add(new ArrayList<>());

            StringTokenizer st;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                data.get(start).add(new Node(end, val));
            }

            st = new StringTokenizer(br.readLine());
            startV = Integer.parseInt(st.nextToken());
            targetV = Integer.parseInt(st.nextToken());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        Queue<Node> q = new PriorityQueue<>();
        int[] parent = new int[n + 1];
        int[] visitDist = new int[n + 1];
        Arrays.fill(visitDist, Integer.MAX_VALUE);

        q.offer(new Node(startV, 0));
        visitDist[startV] = 0;

        Node answerNode = null;
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.vertex == targetV) {
                answerNode = node;
                break;
            }

            for (Node nextNode : data.get(node.vertex)) {
                if (visitDist[nextNode.vertex] <= node.value + nextNode.value)
                    continue;

                q.offer(new Node(nextNode.vertex, node.value + nextNode.value));
                visitDist[nextNode.vertex] = node.value + nextNode.value;
                parent[nextNode.vertex] = node.vertex;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (answerNode != null) {
            makeStringBuilder(sb, answerNode, parent);
        }

        System.out.println(sb);
    }

    private static void makeStringBuilder(StringBuilder sb, Node answerNode, int[] parent) {

        Stack<Integer> s = findAnswer(parent, targetV);

        sb.append(answerNode.value).append("\n");
        sb.append(s.size()).append("\n");
        while (!s.isEmpty())
            sb.append(s.pop()).append(" ");
    }

    private static Stack<Integer> findAnswer(int[] parent, int targetV) {

        Stack<Integer> s = new Stack<>();

        while (targetV != 0) {
            s.push(targetV);
            targetV = parent[targetV];
        }

        return s;
    }

    private static class Node implements Comparable<Node> {
        int vertex, value;

        public Node(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }

        @Override
        public int compareTo(Node node) {
            return this.value - node.value;
        }
    }
}
