package baekjoon_13565;

import java.io.*;
import java.util.*;

public class Main {

    private static int[][] map;
    private static int N, M;
    private static boolean isPossible;
    private static final int[] dR = {0, 0, -1, 1};
    private static final int[] dC = {1, -1, 0, 0};

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        try {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][];

            for(int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        for(int i = 0; i < M; i++) {
            if(map[0][i] != 1)
                bfs(i);
            if(isPossible)
                break;
        }

        if(isPossible)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static void bfs(int col) {

        boolean[][] isVisit = new boolean[N][M];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, col));
        isVisit[0][col] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();

            for(int i = 0; i < 4; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                if(dr < 0 || dc < 0 || dc >= M)
                    continue;
                if(dr >= N) {
                    isPossible = true;
                    continue;
                }
                if(map[dr][dc] == 1)
                    continue;
                if(isVisit[dr][dc])
                    continue;

                isVisit[dr][dc] = true;
                q.offer(new Node(dr, dc));
            }
        }
    }

    private static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
