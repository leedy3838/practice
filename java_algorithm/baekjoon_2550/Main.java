package baekjoon_2550;

import java.io.*;
import java.util.*;

public class Main {

    //LIS에서 이진 탐색에 사용할 List
    private static final List<Integer> dpList = new ArrayList<>();
    //이진 탐색을 이용하면 진짜 순서를 알 수 없기 때문에 trace를 위해서 스택 사용
    private static final Stack<Node> stack = new Stack<>();
    private static int N;
    //실제 switch의 번호
    private static int[] originSwitch;
    //문제 풀이를 위해 사용할 dp - index로 스위치, value로 전구의 idx
    private static int[] dp;

    public static void main(String[] args) {

        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            //0번 index부터 사용
            originSwitch = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            dp = new int[N];

            //dp 생성
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int switchNum = Integer.parseInt(st.nextToken());

                //스위치의 index가 idx 전구의 index가 val
                for (int j = 0; j < N; j++) {
                    if (originSwitch[j] == switchNum) {
                        dp[j] = i;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            int val = dp[i];

            //stack에는 전구의 index를 삽입
            if(dpList.isEmpty() || dpList.get(dpList.size() - 1) < val) {
                dpList.add(val);
                stack.add(new Node(i, dpList.size() - 1));

                cnt++;
            } else {
                int idx = binarySearch(val, dpList.size() - 1);

                dpList.remove(idx);
                dpList.add(idx , val);
                stack.add(new Node(i , idx));
            }
        }

        printAnswer(cnt);
    }

    //lower bound
    private static int binarySearch(int value, int end) {

        int left = 0;
        int right = end;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (dpList.get(mid) < value)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return left;
    }

    private static void printAnswer(int cnt) {

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n");

        List<Integer> answer = new ArrayList<>();

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node.idx == cnt - 1) {
                answer.add(originSwitch[node.arrayIdx]);
                cnt--;
            }
        }

        Collections.sort(answer);

        while(!answer.isEmpty()) {
            sb.append(answer.remove(0)).append(" ");
        }

        System.out.println(sb);
    }

    private static class Node {
        int arrayIdx, idx;

        public Node(int arrayIdx, int idx) {
            this.arrayIdx = arrayIdx;
            this.idx = idx;
        }
    }
}
