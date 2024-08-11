package baekjoon_12908;

import java.io.*;
import java.util.*;

public class Main {

    private static final List<Node> nodeList = new ArrayList<>();
    private static final long[][] dist = new long[8][8];

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int xs = Integer.parseInt(st.nextToken());
        int ys = Integer.parseInt(st.nextToken());
        nodeList.add(new Node(xs, ys, 0));

        st = new StringTokenizer(br.readLine());
        int xe = Integer.parseInt(st.nextToken());
        int ye = Integer.parseInt(st.nextToken());
        nodeList.add(new Node(xe, ye, 0));

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            nodeList.add(new Node(x1, y1, 0));
            nodeList.add(new Node(x2, y2, 0));
        }
    }

    private static void solveProblem() {
        makeDist();

        for (int k = 0; k < 8; k++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        System.out.println(dist[0][1]);
    }

    private static void makeDist() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }

                Node first = nodeList.get(i);
                Node second = nodeList.get(j);

                long distV = Math.abs(first.x - second.x) + Math.abs(first.y - second.y);

                dist[i][j] = distV;
                dist[j][i] = distV;
            }
        }

        for (int i = 7; i >= 2; i -= 2) {
            dist[i][i - 1] = Math.min(dist[i][i - 1], 10);
            dist[i - 1][i] = Math.min(dist[i - 1][i], 10);
        }
    }

    private static class Node {
        int x, y, val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
