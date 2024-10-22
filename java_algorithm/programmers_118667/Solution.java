package programmers_118667;

import java.util.*;

class Solution {
    private long q1Sum = 0, q2Sum = 0;
    private final Queue<Integer> q1 = new ArrayDeque<>();
    private final Queue<Integer> q2 = new ArrayDeque<>();
    
    public int solution(int[] queue1, int[] queue2) {
        setting(queue1, queue2);
        
        if ((q1Sum + q2Sum) % 2 == 1) {
            return -1;
        }
        
        return solve();
    }
    
    private void setting(int[] queue1, int[] queue2) {
        for (int queue : queue1) {
            q1Sum += queue;
            q1.add(queue);
        }
        
        for (int queue : queue2) {
            q2Sum += queue;
            q2.add(queue);
        }
    }
    
    private int solve() {
        int count = 0;
        int qSize = q1.size() + q2.size();
        
        while (true) {
            if (count > qSize + 2) {
                return -1;
            }
            if (q1Sum == q2Sum) {
                break;
            }
            
            if (q1Sum > q2Sum) {
                int v = q1.poll();
                
                if (v > (q1Sum + q2Sum) / 2) {
                    return -1;
                }
                
                q1Sum -= v;
                q2Sum += v;
                q2.add(v);
            } else {
                int v = q2.poll();
                
                if (v > (q1Sum + q2Sum) / 2) {
                    return -1;
                }
                
                q2Sum -= v;
                q1Sum += v;
                q1.add(v);
            }
            
            count++;
        }
        
        return count;
    }
}