package baekjoon_14575;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, T, leftSum = 0, rightSum = 0;
    private static Node[] nodeList;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        nodeList = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int low = Integer.parseInt(st.nextToken());
            int high = Integer.parseInt(st.nextToken());

            nodeList[i] = new Node(low, high);

            leftSum += low;
            rightSum += high;
        }
    }

    private static void solveProblem() {
        long right = T;
        long left = 0;

        if (leftSum > T || rightSum < T) {
            System.out.println(-1);
            return;
        }

        while (left < right) {
            boolean canGo = true;

            long mid = (left + right) / 2;
            long sum = 0;

            for (int i = 0; i < N; i++) {
                Node node = nodeList[i];

                if (node.low > mid) {
                    canGo = false;
                    left = mid + 1;
                    break;
                }

                sum += Math.min(node.high, mid);
            }

            if (!canGo) {
                continue;
            }

            if (sum >= T) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    private static class Node {
        int low, high;

        public Node(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }
}
