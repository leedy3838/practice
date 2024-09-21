package programmers_12978;

import java.util.*;

class Solution {
    private final List<List<Node>> map = new ArrayList<>();
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        makeMap(N, road);

        boolean[] isVisit = new boolean[N + 1];
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            if (!isVisit[node.to]) {
                isVisit[node.to] = true;
                
                if (node.val <= K) {
                    answer++;
                }
            }
            
            for (Node next : map.get(node.to)) {
                if (!isVisit[next.to]) {
                    q.offer(new Node(next.to, node.val + next.val));
                }
            }
        }

        return answer;
    }
    
    private void makeMap(int N, int[][] road) {
        
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }
        
        for (int[] r : road) {
            map.get(r[0]).add(new Node(r[1], r[2]));
            map.get(r[1]).add(new Node(r[0], r[2]));
        }
    }
    
    static class Node implements Comparable<Node> {
        int to, val;
        
        public Node(int to, int val) {
            this.to = to;
            this.val = val;
        }
        
        public int compareTo(Node node) {
            return this.val - node.val;
        }
    }
}