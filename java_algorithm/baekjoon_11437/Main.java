package baekjoon_11437;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Node[] data;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException{

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        List<List<Integer>> l = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            l.add(new ArrayList<>());

        //N - 1개 입력
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            l.get(A).add(B);
            l.get(B).add(A);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisit = new boolean[N + 1];
        data = new Node[N + 1];

        q.offer(1);
        isVisit[1] = true;
        data[1] = new Node(1, 0);

        while (!q.isEmpty()) {
            int parent = q.poll();

            for (int child : l.get(parent)) {
                if(isVisit[child])
                    continue;

                data[child] = new Node(parent, data[parent].rank + 1);
                q.offer(child);
                isVisit[child] = true;
            }
        }
    }

    private static void solveProblem() throws IOException {

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int ARank = data[A].rank;
            int BRank = data[B].rank;

            //A와 B의 rank 맞추기
            while (ARank > BRank) {
                A = data[A].parent;
                ARank = data[A].rank;
            }

            while (BRank > ARank) {
                B = data[B].parent;
                BRank = data[B].rank;
            }

            //공통 조상 찾기
            while (A != B) {
                A = data[A].parent;
                B = data[B].parent;
            }

            sb.append(A).append("\n");
        }

        System.out.println(sb);
    }

    private static class Node {
        int parent, rank;

        public Node(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
}
