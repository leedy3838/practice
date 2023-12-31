package baekjoon_14267;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    //해당 직원의 직속 부하의 번호 저장
    private static final List<List<Integer>> childNum = new ArrayList<>();
    private static int[] goodCnt;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        StringTokenizer st;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for(int i = 0; i <= N; i++)
                childNum.add(new ArrayList<>());
            goodCnt = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int parent = Integer.parseInt(st.nextToken());
                if (parent != -1) childNum.get(parent).add(i);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int personNum = Integer.parseInt(st.nextToken());
                int goodNum = Integer.parseInt(st.nextToken());

                goodCnt[personNum] += goodNum;
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    //bfs, dfs 둘 중 어느 걸로도 해결 가능
    private static void solveProblem() {
        //dfs(1);
        bfs();
        printAnswer();
    }

        private static void dfs(int personNum) {
            for (int nextP : childNum.get(personNum)) {
                goodCnt[nextP] += goodCnt[personNum];
                dfs(nextP);
            }
        }

        private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while (!q.isEmpty()) {
            int person = q.poll();

            for (int nextP : childNum.get(person)) {
                goodCnt[nextP] += goodCnt[person];
                q.offer(nextP);
            }
        }
    }

    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= N; i++)
            sb.append(goodCnt[i]).append(" ");

        System.out.println(sb);
    }
}
