package programmers_요격_시스템;

import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        Queue<Node> q = new PriorityQueue<>();
        for (int[] arr : targets) {
            q.offer(new Node(arr[0], arr[1]));
        }

        while (!q.isEmpty()) {
            Node node = q.poll();

            while (!q.isEmpty() && q.peek().start < node.end) {
                Node removedNode = q.poll();

                if (removedNode.end < node.end) {
                    node.end = removedNode.end;
                }
            }

            answer++;
        }

        return answer;
    }

    private class Node implements Comparable<Node> {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Node node) {
            if (this.start == node.start) {
                return this.end - node.end;
            }
            return this.start - node.start;
        }
    }
}