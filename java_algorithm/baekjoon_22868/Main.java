package baekjoon_22868;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, startV, targetV;
    private static final List<List<Integer>> map = new ArrayList<>();
    private static int[] dist;      //각각의 정점까지 가는 거리
    private static int[] exVertex;   //이 정점에 오기 전의 정점
    private static final Stack<Integer> answer = new Stack<>(); //N이 10000 이하라 contains 사용 -> 순차 접근이므로 N이 크면 배열 사용

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int M  = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= N; i++) {
                map.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                map.get(A).add(B);
                map.get(B).add(A);
            }

            for (int i = 1; i <= N; i++) {
                Collections.sort(map.get(i));
            }

            st = new StringTokenizer(br.readLine());
            startV = Integer.parseInt(st.nextToken());
            targetV = Integer.parseInt(st.nextToken());

            initSetting();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {
        dijkstra(startV, targetV);
        dijkstra(targetV, startV);
        System.out.println(answer.size());
    }

    private static void dijkstra(int start, int end) {

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int vertex = q.poll();

            if (vertex == end) {
                break;
            }

            for (int nextV : map.get(vertex)) {
                if (answer.contains(nextV))
                    continue;
                if (dist[nextV] <= dist[vertex] + 1)
                    continue;

                q.offer(nextV);
                dist[nextV] = dist[vertex] + 1;
                exVertex[nextV] = vertex;
            }
        }

        addToStack(end);
        initSetting();
    }

    private static void initSetting() {
        exVertex = new int[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
    }

    private static void addToStack(int endV) {
        while (exVertex[endV] != 0) {
            answer.push(endV);
            endV = exVertex[endV];
        }
    }
}
