package programmers_49189;

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {        
        boolean[] isVisit = new boolean[n + 1];
        
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            l.add(new ArrayList<>());
        }
        
        for (int[] e : edge) {
            l.get(e[0]).add(e[1]);
            l.get(e[1]).add(e[0]);
        }
        
        int answer = 0;
        int nowDist = 0;
        
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));
        isVisit[1] = true;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            if (node.dist > nowDist) {
                nowDist = node.dist;
                answer = 1;
            } else {
                answer++;
            }
            
            for (int next : l.get(node.idx)) {
                if (isVisit[next])
                    continue;
                
                q.offer(new Node(next, node.dist + 1));
                isVisit[next] = true;
            }
        }
        
        return answer;
    }
    
    static class Node implements Comparable<Node> {
        int idx, dist;
        
        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
        
        public int compareTo(Node node) {
            return this.dist - node.dist;
        }
    }
}