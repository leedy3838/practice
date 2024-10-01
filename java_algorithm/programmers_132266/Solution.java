package programmers_132266;

import java.util.*;

class Solution {
    private final List<List<Integer>> l = new ArrayList<>();
    private boolean[] isVisit;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        setting(n, roads);
        
        List<Integer> ans = new ArrayList<>();
        for (int source : sources) {
            ans.add(bfs(n, source, destination));
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private void setting(int n, int[][] roads) {
        for (int i = 0; i <= n; i++) {
            l.add(new ArrayList<>());
        }
        
        isVisit = new boolean[n + 1];
        
        for (int[] road : roads) {
            l.get(road[0]).add(road[1]);
            l.get(road[1]).add(road[0]);
        }
    }
    
    private int bfs(int n, int start, int dest) {
        if (start == dest) {
            return 0;
        }
        
        Arrays.fill(isVisit, false);
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        isVisit[start] = true;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            for (int next : l.get(node.val)) {
                if (isVisit[next])
                    continue;
                
                if (next == dest) {
                    return node.dist + 1;
                }
                
                q.offer(new Node(next, node.dist + 1));
                isVisit[next] = true;
            }
        }
        
        return -1;
    }
    
    static class Node {
        int val, dist;
        
        public Node(int val, int dist) { 
            this.val = val;
            this.dist = dist;
        }
    }
}