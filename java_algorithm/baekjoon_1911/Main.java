package baekjoon_1911;

import java.util.*;
import java.io.*;

public class Main {

    private static int L;
    private static final Queue<Node> pools = new PriorityQueue<>();

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            while (N-- > 0) {
                st = new StringTokenizer(br.readLine());
                
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                
                pools.add(new Node(start, end));
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int count = 0, coverage = 0;

        while (!pools.isEmpty()) {
            Node node = pools.poll();

            //현재 널빤지로 덮고 있는 범위 밖이면 새로운 널빤지
            if (node.start > coverage) {
                count++;
                coverage = node.start + L;
            }

            //현재 널빤지로 덮고 있는 범위 이내면 추가로 이어 붙이기
            while (node.end > coverage) {
                count++;
                coverage += L;
            }
        }

        System.out.println(count);
    }

    private static class Node implements Comparable<Node> {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node node) {
            return this.start - node.start;
        }
    }
}
