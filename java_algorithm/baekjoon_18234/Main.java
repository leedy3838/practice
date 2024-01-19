package baekjoon_18234;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, T;
    private static final List<Node> list = new ArrayList<>();

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int w = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                list.add(new Node(w, p));
            }

            list.sort(Comparator.comparingInt(node -> node.p));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        long answer = 0;

        for (int i = 0; i < N; i++) {
            long result = (long) (i + T - N) * list.get(i).p + list.get(i).w;
            answer += result;
        }

        System.out.println(answer);
    }

    private static class Node{
        int w, p;

        public Node(int w, int p) {
            this.w = w;
            this.p = p;
        }
    }
}
