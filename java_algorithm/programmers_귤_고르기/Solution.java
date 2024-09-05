package programmers_귤_고르기;

import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();

        Arrays.stream(tangerine)
                .forEach(val -> {
                    int cnt = map.getOrDefault(val, 0);
                    map.put(val, cnt + 1);
                });

        Queue<Node> q = new PriorityQueue<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            q.offer(new Node(entry.getKey(), entry.getValue()));
        }

        int sum = 0;
        while (sum < k) {
            Node node = q.poll();

            sum += node.cnt;
            answer++;
        }

        return answer;
    }

    private static class Node implements Comparable<Node> {
        int val, cnt;

        public Node(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }

        public int compareTo(Node node) {
            return node.cnt - this.cnt;
        }
    }
}