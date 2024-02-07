package baekjoon_9372;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[] visit;
    static int N, M, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            result = 0;

            map = new int[N + 1][N + 1];
            visit = new boolean[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                map[u][v] = 1;
                map[v][u] = 1;
            }

            bfs();
            sb.append(result - 1).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;

        while (!q.isEmpty()) {
            int value = q.poll();
            result++;

            for (int i = 1; i <= N; i++) {
                if (map[value][i] != 0 && !visit[i]) {
                    visit[i] = true;
                    q.add(i);
                }
            }
        }
    }
}
