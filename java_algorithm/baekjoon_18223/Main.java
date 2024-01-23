package baekjoon_18223;

import java.io.*;
import java.util.*;

public class Main {

    private static int V, P;
    private static final List<List<Node>> data = new ArrayList<>();

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= V; i++)
                data.add(new ArrayList<>());

            while (E-- > 0) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                data.get(A).add(new Node(B, val));
                data.get(B).add(new Node(A, val));
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int OneToP = dijkstra(1, P);
        int PToV = dijkstra(P, V);
        int OneToV = dijkstra(1, V);

        if (OneToV == OneToP + PToV)
            System.out.println("SAVE HIM");
        else
            System.out.println("GOOD BYE");
    }

    private static int dijkstra(int startV, int targetV) {

        Queue<Node> q = new PriorityQueue<>();
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        q.offer(new Node(startV, 0));
        dist[startV] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.vertex == targetV) {
                return node.val;
            }

            for (Node nextNode : data.get(node.vertex)) {
                if (dist[nextNode.vertex] <= node.val + nextNode.val) {
                    continue;
                }

                q.offer(new Node(nextNode.vertex, nextNode.val + node.val));
                dist[nextNode.vertex] = node.val + nextNode.val;
            }
        }

        return -1;
    }

    private static class Node implements Comparable<Node>{
        int vertex, val;

        public Node(int vertex, int val) {
            this.vertex = vertex;
            this.val = val;
        }

        @Override
        public int compareTo(Node node) {
            return this.val - node.val;
        }
    }
}
