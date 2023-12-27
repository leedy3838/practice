package baekjoon_17265;

import java.io.*;
import java.util.*;

public class Main {

    //우, 하만 존재
    private static final int[] dR = {0, 1};
    private static final int[] dC = {1, 0};
    private static char[][] map;
    private static int N;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][];

            for (int i = 0; i < N; i++)
                map[i] = br.readLine()
                        .replaceAll(" ", "")
                        .toCharArray();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int maxVal = findMaxVal();
        int minVal = findMinVal();

        System.out.println(maxVal + " " + minVal);
    }

    private static int findMinVal() {

        int[][] minValMap  = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(minValMap[i], 100_000);
        Queue<Node> q = new LinkedList<>();

        //정수값으로 변환
        minValMap[0][0] = map[0][0] - '0';
        q.offer(new Node(0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < dR.length; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                //우 하로만 이동하기 때문에 이 범위만 확인
                if (dr >= N || dc >= N)
                    continue;
                //map[dr][dc]에 있는 값이 연산자가 아닌 경우
                if ('0' <= map[dr][dc] && map[dr][dc] <= '5')
                    continue;

                //map[dr][dc]가 연산자인 경우
                for(int j = 0; j < dR.length; j++) {
                    int nextR = dr + dR[j];
                    int nextC = dc + dC[j];

                    //우 하로만 이동하기 때문에 이 범위만 확인
                    if (nextR >= N || nextC >= N)
                        continue;

                    int exVal = minValMap[node.row][node.col];
                    int nextVal = map[nextR][nextC] - '0';

                    switch (map[dr][dc]) {
                        case '+':
                            minValMap[nextR][nextC] = Math.min(minValMap[nextR][nextC], exVal + nextVal);
                            break;
                        case '-':
                            minValMap[nextR][nextC] = Math.min(minValMap[nextR][nextC], exVal - nextVal);
                            break;
                        case '*':
                            minValMap[nextR][nextC] = Math.min(minValMap[nextR][nextC], exVal * nextVal);
                            break;
                    }

                    q.offer(new Node(nextR, nextC));
                }
            }
        }

        return minValMap[N - 1][N - 1];
    }

    private static int findMaxVal() {
        int[][] maxValMap = new int[N][N];
        //최대값이 0이 아닐 수도 있기 때문
        for (int i = 0; i < N; i++)
            Arrays.fill(maxValMap[i], -100_000);
        Queue<Node> q = new LinkedList<>();

        //정수값으로 변환
        maxValMap[0][0] = map[0][0] - '0';
        q.offer(new Node(0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < dR.length; i++) {
                int dr = node.row + dR[i];
                int dc = node.col + dC[i];

                //우 하로만 이동하기 때문에 이 범위만 확인
                if (dr >= N || dc >= N)
                    continue;
                //map[dr][dc]에 있는 값이 연산자가 아닌 경우
                if ('0' <= map[dr][dc] && map[dr][dc] <= '5')
                    continue;

                //map[dr][dc]가 연산자인 경우
                for (int j = 0; j < dR.length; j++) {
                    int nextR = dr + dR[j];
                    int nextC = dc + dC[j];

                    //우 하로만 이동하기 때문에 이 범위만 확인
                    if (nextR >= N || nextC >= N)
                        continue;

                    int exVal = maxValMap[node.row][node.col];
                    int nextVal = map[nextR][nextC] - '0';

                    switch (map[dr][dc]) {
                        case '+':
                            maxValMap[nextR][nextC] = Math.max(maxValMap[nextR][nextC], exVal + nextVal);
                            break;
                        case '-':
                            maxValMap[nextR][nextC] = Math.max(maxValMap[nextR][nextC], exVal - nextVal);
                            break;
                        case '*':
                            maxValMap[nextR][nextC] = Math.max(maxValMap[nextR][nextC], exVal * nextVal);
                            break;
                    }

                    q.offer(new Node(nextR, nextC));
                }
            }
        }

        return maxValMap[N - 1][N - 1];
    }

    private static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
