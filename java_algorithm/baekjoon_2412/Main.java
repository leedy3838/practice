package baekjoon_2412;

import java.io.*;
import java.util.*;

public class Main {

    private static int T;
    private static final List<List<Integer>> l = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   //n개의 홈
        T = Integer.parseInt(st.nextToken());   //목표 y 좌표

        for (int i = 0; i <= T; i++) {
            l.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            l.get(y).add(x);
        }
    }

    private static void solveProblem() {
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));

        int ans = -1;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.y == T) {
                return node.cnt;
            }

            for (int nextY = node.y - 2; nextY <= node.y + 2; nextY++) {
                if (nextY < 0 || nextY > T)
                    continue;

                for (int nextXIdx = 0; nextXIdx < l.get(nextY).size(); nextXIdx++) {
                    int nextX = l.get(nextY).get(nextXIdx);

                    if (nextX < 0)
                        continue;

                    if (Math.abs(node.x - nextX) <= 2) {
                        q.offer(new Node(nextX, nextY, node.cnt + 1));
                        l.get(nextY).remove(nextXIdx);
                        nextXIdx--;
                    }
                }
            }
        }

        return ans;
    }

    private static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
