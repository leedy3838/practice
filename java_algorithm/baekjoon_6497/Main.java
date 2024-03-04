package baekjoon_6497;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int m;
    private static List<List<Node>> lineData;
    private static int distSum = 0;
    private static boolean endFlag = false;

    public static void main(String[] args) {
        solveProblem();
    }

    private static void firstSetting() {

        try {
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                endFlag = true;
                return;
            }

            distSum = 0;
            lineData = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                lineData.add(new ArrayList<>());
            }

            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                distSum += dist;

                lineData.get(A).add(new Node(B, dist));
                lineData.get(B).add(new Node(A, dist));
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        StringBuilder sb = new StringBuilder();

        while (true) {
            firstSetting();
            if (endFlag) {
                break;
            }

            int dist = 0;
            boolean[] isVisit = new boolean[m];
            Queue<Node> q = new PriorityQueue<>();
            q.offer(new Node (0, 0));

            while (!q.isEmpty()) {
                Node node = q.poll();

                //이미 방문한 정점
                if (isVisit[node.vertex]) {
                    continue;
                }

                //방문하지 않은 정점인 경우
                dist += node.dist;
                isVisit[node.vertex] = true;

                for (Node nextNode : lineData.get(node.vertex)) {
                    if (isVisit[nextNode.vertex]) {
                        continue;
                    }

                    q.offer(nextNode);
                }
            }

            sb.append(distSum - dist).append("\n");
        }

        System.out.println(sb);
    }

    private static class Node implements Comparable<Node> {
        int vertex, dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return this.dist - node.dist;
        }
    }
}
