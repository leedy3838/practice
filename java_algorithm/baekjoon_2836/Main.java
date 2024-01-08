package baekjoon_2836;

import java.io.*;
import java.util.*;

public class Main {

    private static int M;
    private static final Queue<Node> l = new PriorityQueue<>();

    public static void main(String[] args) {
        solveProblem();
    }

    private static void solveProblem() {
        inputData();
        System.out.println(getAnswer());
    }

    private static long getAnswer() {
        long sum = M;
        int start = 0;
        int end = 0;

        while (!l.isEmpty()) {
            Node node = l.poll();

            if (node.from <= end) {  //이어진 경우
                end = Math.max(end, node.to);
            } else {                //이어지지 않은 경우
                sum += (end - start) * 2L;

                start = node.from;
                end = node.to;
            }
        }
        sum += (end - start) * 2L;
        return sum;
    }

    private static void inputData() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            while (N-- > 0) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to  = Integer.parseInt(st.nextToken());

                if (from <= to)
                    continue;

                //입력은 역방향이지만 l에 순방향으로 삽입
                l.offer(new Node(to, from));
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static class Node implements Comparable<Node> {
        int from, to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Node node) {
            if(this.from == node.from)
                return node.to - this.to;
            return this.from - node.from;
        }
    }
}
