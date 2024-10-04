package programmers_72413;

import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int n, s, a, b;
    
    List<List<Node>> l = new ArrayList<>();
    Set<Integer> linkedIdx = new HashSet<>();
    boolean[] isVisit;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        this.s = s;
        this.a = a;
        this.b = b;
        
        setting(fares);
        solve();
        
        return answer;
    }
    
    private void setting(int[][] fares) {
        for (int i = 0; i <= n; i++) {
            l.add(new ArrayList<>());
        }
        
        for (int[] fare : fares) {
            int A = fare[0];
            int B = fare[1];
            int val = fare[2];
            
            l.get(A).add(new Node(B, val));
            l.get(B).add(new Node(A, val));
        }
        
        isVisit = new boolean[n + 1];
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        linkedIdx.add(s);
        isVisit[s] = true;
        
        while (!q.isEmpty()) {
            int idx = q.poll();
            
            for (Node next : l.get(idx)) {
                if (isVisit[next.idx]) {
                    continue;
                }
                
                q.offer(next.idx);
                linkedIdx.add(next.idx);
                isVisit[next.idx] = true;
            }
        }
    }
    
    private void solve() {
        int[] sDist = dijkstra(s);
        int[] aDist = dijkstra(a);
        int[] bDist = dijkstra(b);
        
        for (int i = 1; i <= n; i++) {
            if (!linkedIdx.contains(i)) {
                continue;
            }
            
            int price = sDist[i] + aDist[i] + bDist[i];
            answer = Math.min(answer, price);
        }
    }
    
    private int[] dijkstra(int start) {
        int[] minDist = new int[n + 1];
        
        Arrays.fill(minDist, Integer.MAX_VALUE);
        Arrays.fill(isVisit, false);
        
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));
        minDist[start] = 0;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            isVisit[node.idx] = true;
            minDist[node.idx] = Math.min(minDist[node.idx], node.val);
            
            for (Node next : l.get(node.idx)) {
                if (isVisit[next.idx]) {
                    continue;
                }
                
                q.offer(new Node(next.idx, node.val + next.val));
            }
        }
        
        return minDist;
    }
    
    static class Node implements Comparable<Node> {
        int idx, val;
        
        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
        
        public int compareTo(Node node) {
            return this.val - node.val;
        }
    }
}