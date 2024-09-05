package programmers_숫자_변환하기;

import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;

        Queue<Integer> q = new ArrayDeque<>();
        int[] visitCnt = new int[y + 1];
        Arrays.fill(visitCnt, Integer.MAX_VALUE);
        visitCnt[x] = 0;

        q.offer(x);

        while (!q.isEmpty()) {
            int val = q.poll();

            if (visitCnt[y] != Integer.MAX_VALUE) {
                break;
            }

            if (val * 3 <= y && visitCnt[val] + 1 < visitCnt[val * 3]) {
                visitCnt[val * 3] = visitCnt[val] + 1;
                q.offer(val * 3);
            }

            if (val * 2 <= y && visitCnt[val] + 1 < visitCnt[val * 2]) {
                visitCnt[val * 2] = visitCnt[val] + 1;
                q.offer(val * 2);
            }

            if (val + n <= y && visitCnt[val] + 1 < visitCnt[val + n]) {
                visitCnt[val + n] = visitCnt[val] + 1;
                q.offer(val + n);
            }
        }

        if (visitCnt[y] != Integer.MAX_VALUE) {
            answer = visitCnt[y];
        }

        return answer;
    }
}