package programmers_118669;

import java.util.*;

class Solution {
    List<List<Node>> l = new ArrayList<>();
    boolean[] summits;
    boolean[] gates;
    
    int mountain = Integer.MAX_VALUE;
    int intensity = Integer.MAX_VALUE;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        makeMap(n, paths, summits, gates);
        
        for (int gate : gates) {
            dijkstra(gate, n);
        }
        
        int[] answer = {mountain, intensity};
        return answer;
    }
    
    private void makeMap(int n, int[][] paths, int[] summits, int[] gates) {
        for (int i = 0; i <= n; i++) {
            l.add(new ArrayList<>());
        }
        
        for (int[] path : paths) {
            l.get(path[0]).add(new Node(path[1], path[2]));
            l.get(path[1]).add(new Node(path[0], path[2]));
        }
        
        this.summits = new boolean[n + 1];
        this.gates = new boolean[n + 1];
        
        for (int summit : summits) {
            this.summits[summit] = true;
        }
        
        for (int gate : gates) {
            this.gates[gate] = true;
        }
    }
    
    private void dijkstra(int idx, int n) {
        boolean[] isVisit = new boolean[n + 1];

        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(idx, 0));
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            if (isVisit[node.to])
                continue;
            
            isVisit[node.to] = true;
            
            if (summits[node.to]) {
                if (node.val < intensity) {
                    intensity = node.val;
                    mountain = node.to;
                } else if (node.val == intensity) {
                    mountain = Math.min(mountain, node.to);
                }
                
                continue;
            }
            
            for (Node next : l.get(node.to)) {
                if (isVisit[next.to])
                    continue;
                if (next.val > intensity)
                    continue;
                if (gates[next.to])
                    continue;

                
                q.offer(new Node (next.to, Math.max(node.val, next.val)));
            }
        }
    }
    
    class Node implements Comparable<Node> {
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