package baekjoon_21610;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dR = new int[]{-10, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dC = new int[]{-10, -1, -1, 0, 1, 1, 1, 0, -1};

    private static int N, M;
    private static int[][] A;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solveProblem() throws IOException {
        StringTokenizer st;

        Queue<Node> cloudList = new LinkedList<>();
        boolean[][] exVisit = new boolean[N + 1][N + 1];

        for (int loop = 0; loop < M; loop++) {
            if (loop == 0) {
                cloudList.offer(new Node(N, 1));
                cloudList.offer(new Node(N, 2));
                cloudList.offer(new Node(N - 1, 1));
                cloudList.offer(new Node(N - 1, 2));
            }

            int cloudSize = cloudList.size();

            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            while (cloudSize-- > 0) {
                Node movedLocation = moveCloud(cloudList.poll(), d, s);

                A[movedLocation.row][movedLocation.col]++;
                exVisit[movedLocation.row][movedLocation.col] = true;

                cloudList.offer(movedLocation);
            }

            while (!cloudList.isEmpty()) {
                Node node = cloudList.poll();

                int cnt = checkCross(node);
                A[node.row][node.col] += cnt;
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (A[i][j] >= 2 && !exVisit[i][j]) {
                        cloudList.add(new Node(i, j));
                        A[i][j] -= 2;
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                Arrays.fill(exVisit[i], false);
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ans += A[i][j];
            }
        }
        System.out.println(ans);
    }

    private static Node moveCloud(Node node, int d, int s) {
        int newRow = (node.row + dR[d] * s) % N;
        int newCol = (node.col + dC[d] * s) % N;

        while (newRow <= 0) {
            newRow += N;
        }

        while (newCol <= 0) {
            newCol += N;
        }

        return new Node(newRow, newCol);
    }

    private static int checkCross(Node node) {
        int cnt = 0;

        for (int i = 2; i <= 8; i += 2) {
            int dr = node.row + dR[i];
            int dc = node.col + dC[i];

            if (dr < 1 || dc < 1 || dr > N || dc > N) {
                continue;
            }

            if (A[dr][dc] != 0) {
                cnt++;
            }
        }

        return cnt;
    }

    private static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
