package baekjoon_2513;

import java.io.*;
import java.util.*;

public class Main {

    private static int K;
    private static final Queue<Node> prevSchool = new PriorityQueue<>();
    private static final Queue<Node> afterSchool = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //아파트 단지의 수
        K = Integer.parseInt(st.nextToken());   //통학버스의 정원
        int S = Integer.parseInt(st.nextToken());   //학교의 위치

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int apart = Integer.parseInt(st.nextToken());
            int peopleNum = Integer.parseInt(st.nextToken());

            if (apart < S) {
                prevSchool.add(new Node(S - apart, peopleNum));
            } else {
                afterSchool.add(new Node(apart - S, peopleNum));
            }
        }
    }

    private static void solveProblem() {
        int distSum = 0;

        int nowMaxDist = 0;
        int nowBusRide = 0;
        while (!prevSchool.isEmpty()) {
            Node node = prevSchool.poll();

            nowBusRide += node.peopleNum;
            nowMaxDist = Math.max(nowMaxDist, node.dist);

            if (nowBusRide > K) {
                //다시 버스를 타야 하는 사람들
                prevSchool.add(new Node(node.dist, nowBusRide - K));

                distSum += nowMaxDist * 2;
                nowMaxDist = 0;
                nowBusRide = 0;
            } else if (nowBusRide == K) {
                distSum += nowMaxDist * 2;
                nowMaxDist = 0;
                nowBusRide = 0;
            }
        }
        distSum += nowMaxDist * 2;

        nowMaxDist = 0;
        nowBusRide = 0;
        while (!afterSchool.isEmpty()) {
            Node node = afterSchool.poll();

            nowBusRide += node.peopleNum;
            nowMaxDist = Math.max(nowMaxDist, node.dist);

            if (nowBusRide > K) {
                //다시 버스를 타야 하는 사람들
                afterSchool.add(new Node(node.dist, nowBusRide - K));

                distSum += nowMaxDist * 2;
                nowMaxDist = 0;
                nowBusRide = 0;
            } else if (nowBusRide == K) {
                distSum += nowMaxDist * 2;
                nowMaxDist = 0;
                nowBusRide = 0;
            }
        }
        distSum += nowMaxDist * 2;

        System.out.println(distSum);
    }

    private static class Node implements Comparable<Node> {
        int dist, peopleNum;

        public Node(int dist, int peopleNum) {
            this.dist = dist;
            this.peopleNum = peopleNum;
        }

        //학교에서 떨어진 절대값이 큰 순으로 정렬
        public int compareTo(Node node) {
            return Math.abs(node.dist) - Math.abs(this.dist);
        }
    }
}
