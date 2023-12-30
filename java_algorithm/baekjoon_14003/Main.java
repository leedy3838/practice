package baekjoon_14003;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] data;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            data = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        ArrayList<Integer> array = new ArrayList<>();
        Node[] record = new Node[N];

        for (int i = 0; i < N; i++) {
            int val = data[i];

            if (array.isEmpty() || array.get(array.size() - 1) < val) {
                array.add(val);
                //아래의 else에서 idx를 구할 때 0번 idx가 나올 수 있으므로 0 ~ size - 1의 범위 설정
                record[i] = new Node(val, array.size() - 1);
            } else {
                int idx = binarySearch(val, array);

                array.set(idx, val);
                record[i] = new Node(val, idx);
            }
        }

        printAnswer(record, array);
    }

    //lower bound
    private static int binarySearch(int data, List<Integer> array) {

        int left = 0;
        int right = array.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array.get(mid) < data)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return left;
    }

    private static void printAnswer(Node[] record, List<? extends Integer> array) {

        StringBuilder sb = new StringBuilder();
        sb.append(array.size()).append("\n");

        Stack<Integer> answer = new Stack<>();
        int idx = array.size() - 1;
        for (int i = record.length - 1; i >= 0; i--) {
            Node node = record[i];

            if (node.idx == idx) {
                answer.push(node.val);
                idx--;
            }
        }

        while(!answer.isEmpty()) {
            sb.append(answer.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static class Node {
        int val, idx;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}
