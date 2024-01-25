package baekjoon_16168;

import java.io.*;
import java.util.*;

public class Main {

    private static int V, E;
    private static final List<List<Integer>> l = new ArrayList<>();
    private static int[][] isVisit;
    private static boolean isPossible = false;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= V; i++) {
                l.add(new ArrayList<>());
            }
            isVisit = new int[V + 1][V + 1];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                l.get(A).add(B);
                l.get(B).add(A);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        for (int i = 1; i <= V; i++) {
            pathFind(i, i, 0);
        }

        if (isPossible)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static void pathFind(int vertex, int num, int cnt) {

        if (cnt == E) {
            isPossible = true;
            return;
        }

        for (int nextV : l.get(vertex)) {
            if (isVisit[vertex][nextV] == num && isVisit[nextV][vertex] == num)
                continue;

            isVisit[vertex][nextV] = num;
            isVisit[nextV][vertex] = num;

            pathFind(nextV, num, cnt + 1);
        }
    }
}
