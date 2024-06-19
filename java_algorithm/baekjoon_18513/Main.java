package baekjoon_18513;

import java.io.*;
import java.util.*;

public class Main {

    private static int K;
    private static final List<Integer> startNList = new ArrayList<>();
    private static final int[] dV = new int[]{1, -1};

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            startNList.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static void solveProblem() {
        long answerSum = 0;
        int houseCnt = 0;
        Set<Integer> isVisit = new HashSet<>();

        Queue<Node> pq = new PriorityQueue<>();
        for (Integer idx : startNList) {
            pq.add(new Node(idx, 0));
            isVisit.add(idx);
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < 2; i++) {
                int nextIdx = node.idx + dV[i];

                if (isVisit.contains(nextIdx)) {
                    continue;
                }

                answerSum += node.weight + 1;
                pq.add(new Node(nextIdx, node.weight + 1));
                isVisit.add(nextIdx);
                houseCnt++;

                if (houseCnt == K) {
                    System.out.println(answerSum);
                    return;
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int idx, weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
}
