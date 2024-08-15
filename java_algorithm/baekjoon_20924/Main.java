package baekjoon_20924;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, R;
    private static final List<List<Node>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            tree.get(A).add(new Node(B, val, true));
            tree.get(B).add(new Node(A, val, true));
        }
    }

    private static void solveProblem() {
        int maxBodyVal = 0;
        int maxLeafVal = 0;

        boolean[] isVisit = new boolean[N + 1];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(R, 0, true));
        isVisit[R] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.isBody) {
                maxBodyVal = Math.max(maxBodyVal, node.val);
            } else {
                maxLeafVal = Math.max(maxLeafVal, node.val);
            }

            if (node.idx == R) {    //루트
                if (tree.get(node.idx).size() > 1) {    //가지
                    for (Node nextNode : tree.get(node.idx)) {
                        q.offer(new Node(nextNode.idx, nextNode.val, false));
                        isVisit[nextNode.idx] = true;
                    }
                } else {                                //몸통
                    for (Node nextNode : tree.get(node.idx)) {
                        q.offer(new Node(nextNode.idx, nextNode.val, true));
                        isVisit[nextNode.idx] = true;
                    }
                }
            } else {                //루트 X
                if (node.isBody) {
                    if (tree.get(node.idx).size() > 2) {    //가지
                        for (Node nextNode : tree.get(node.idx)) {
                            if (isVisit[nextNode.idx])
                                continue;

                            if (node.isBody) {
                                q.offer(new Node(nextNode.idx, nextNode.val, false));
                            } else {
                                q.offer(new Node(nextNode.idx, node.val + nextNode.val, false));
                            }

                            isVisit[nextNode.idx] = true;
                        }
                    } else {    //몸통
                        for (Node nextNode : tree.get(node.idx)) {
                            if (isVisit[nextNode.idx])
                                continue;

                            q.offer(new Node(nextNode.idx, node.val + nextNode.val, true));
                            isVisit[nextNode.idx] = true;
                        }
                    }
                } else {
                    for (Node nextNode : tree.get(node.idx)) {
                        if (isVisit[nextNode.idx])
                            continue;

                        q.offer(new Node(nextNode.idx, node.val + nextNode.val, false));
                        isVisit[nextNode.idx] = true;
                    }
                }
            }
        }

        System.out.println(maxBodyVal + " " + maxLeafVal);
    }

    private static class Node {
        int idx, val;
        boolean isBody;

        public Node(int idx, int val, boolean isBody) {
            this.idx = idx;
            this.val = val;
            this.isBody = isBody;
        }
    }
}
