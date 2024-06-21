package baekjoon_16434;

import java.io.*;
import java.util.*;

public class Main {

    private static int H;
    private static Node[] data;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        data = new Node[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            data[i] = new Node(t, a, h);
        }
    }

    private static void solveProblem() {
        long curNeedH = 0, minNeedH = 0, curA = H;

        for (Node node : data) {
            if (node.t == 1) {  //몬스터
                curNeedH += node.a * ((node.h / curA) - ((node.h % curA == 0) ? 1 : 0));
                minNeedH = Math.max(minNeedH, curNeedH);
            } else {            //포션
                curA += node.a;
                curNeedH = Math.max(curNeedH - node.h, 0);
            }
        }

        System.out.println(minNeedH + 1);
    }

    private static class Node {
        int t, a, h;

        public Node(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }
}
