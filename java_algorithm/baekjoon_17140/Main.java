package baekjoon_17140;

import java.io.*;
import java.util.*;

public class Main {

    private static int r, c, k;
    private static final int[][] map = new int[101][101];

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solveProblem() {
        int maxR = 3, maxC = 3;     //최대 row, col 수
        int time = 0;

        while (map[r][c] != k) {
            time++;

            if (time > 100) {
                break;
            }

            if (maxR >= maxC) {
                maxC = operationR(maxR, maxC);
            } else {
                maxR = operationC(maxC, maxR);
            }
        }
        if (time > 100) {
            System.out.println(-1);
        } else {
            System.out.println(time);
        }
    }

    private static int operationR(int maxR, int maxC) {
        int nextMaxC = 0;

        for (int i = 1; i <= maxR; i++) {   //행에 대한 연산
            int[] numCount = new int[101];          //index - num, value - count
            Queue<Node> pq = new PriorityQueue<>(); //numCount의 값을 넣을 용도 - count가 적은 게 먼저 나옴

            int j = 1;
            while (j <= maxC) {
                numCount[map[i][j]]++;
                j++;
            }

            for (int num = 1; num <= 100; num++) {
                if (numCount[num] != 0) {
                    pq.add(new Node(num, numCount[num]));
                }
            }

            int idx = 1;
            while (!pq.isEmpty()) {
                Node node = pq.poll();

                if (idx > 100) {
                    break;
                }

                map[i][idx++] = node.num;
                map[i][idx++] = node.count;
            }

            nextMaxC = Math.max(nextMaxC, idx - 1);

            while (idx <= 100) {
                map[i][idx++] = 0;        //map 초기화
            }
        }

        maxC = nextMaxC;
        return maxC;
    }

    private static int operationC(int maxC, int maxR) {
        int nextMaxR = 0;

        for (int i = 1; i <= maxC; i++) {   //열에 대한 연산
            int[] numCount = new int[101];          //index - num, value - count
            Queue<Node> pq = new PriorityQueue<>(); //numCount의 값을 넣을 용도 - count가 적은 게 먼저 나옴

            int j = 1;
            while (j <= maxR) {
                numCount[map[j][i]]++;
                j++;
            }

            for (int num = 1; num <= 100; num++) {
                if (numCount[num] != 0) {
                    pq.add(new Node(num, numCount[num]));
                }
            }

            int idx = 1;
            while (!pq.isEmpty()) {
                Node node = pq.poll();

                if (idx > 100) {
                    break;
                }

                map[idx++][i] = node.num;
                map[idx++][i] = node.count;
            }

            nextMaxR = Math.max(nextMaxR, idx - 1);

            while (idx <= 100) {
                map[idx++][i] = 0;        //map 초기화
            }
        }

        maxR = nextMaxR;
        return maxR;
    }

    private static class Node implements Comparable<Node> {
        int num, count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }

        public int compareTo(Node node) {
            if (this.count == node.count) {
                return this.num - node.num; //num 오름 차순
            }
            return this.count - node.count; //count 오름 차순
        }
    }
}
