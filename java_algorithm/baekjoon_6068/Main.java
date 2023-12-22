package baekjoon_6068;

import java.io.*;
import java.util.*;

public class Main {

    private static final boolean[] timeTable = new boolean[1_000_000];
    private static final Queue<Node> q = new PriorityQueue<>();

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int needTime = Integer.parseInt(st.nextToken());
                int endTime = Integer.parseInt(st.nextToken());

                q.offer(new Node(needTime, endTime));
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        while(!q.isEmpty()) {
            if(!putToTimeTable(q.poll())){
                System.out.println(-1);
                return;
            }
        }

        printAnswer();
    }

    private static boolean putToTimeTable(Node node) {

        int needTime = node.needTime;
        int endTime = node.endTime;

        while(needTime > 0) {
            //정상적으로 실행 가능
            if(!timeTable[endTime]) {
                timeTable[endTime] = true;
                needTime--;
            }

            //정상적으로 실행 불가능
            if(--endTime < 0)
                return false;
        }

        return true;
    }

    private static void printAnswer() {

        for(int i = 0; i <= 1_000_000; i++) {
            if(timeTable[i]) {
                System.out.println(i - 1);
                return;
            }
        }
    }

    private static class Node implements Comparable<Node>{
        int needTime, endTime;

        public Node(int needTime, int endTime) {
            this.needTime = needTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Node node) {
            return node.endTime - this.endTime;
        }
    }
}
