package programmers_86971;

import java.util.*;

class Solution {
    private final List<List<Integer>> l = new ArrayList<>();
    private boolean[] isVisit;
    
    public int solution(int n, int[][] wires) {
        setting(n, wires);
        
        int answer = Integer.MAX_VALUE;
        
        for (int[] wire : wires) {
            answer = Math.min(answer, solve(wire));
        }
        
        return answer;
    }
    
    private void setting(int n, int[][] wires) {
        for (int i = 0; i <= n; i++) {
            l.add(new ArrayList<>());
        }
        isVisit = new boolean[n + 1];
        
        for (int[] wire : wires) {
            l.get(wire[0]).add(wire[1]);
            l.get(wire[1]).add(wire[0]);
        }
    }
    
    private int solve(int[] wire) {
        Arrays.fill(isVisit, false);
        isVisit[wire[0]] = true;
        isVisit[wire[1]] = true;
        
        return Math.abs(bfs(wire[0]) - bfs(wire[1]));
    }
    
    private int bfs(int start) {
        int cnt = 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        cnt++;
        
        while (!q.isEmpty()) {
            int vertex = q.poll();
            
            for (int next : l.get(vertex)) {
                if (isVisit[next])
                    continue;
                
                q.offer(next);
                isVisit[next] = true;
                cnt++;
            }
        }
        
        return cnt;
    }
}