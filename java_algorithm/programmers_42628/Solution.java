package programmers_42628;

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        StringTokenizer st;
        
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((num1, num2) -> num2 - num1);
        
        for (String operation : operations) {
            st = new StringTokenizer(operation);
            
            String op = st.nextToken();
            int val = Integer.parseInt(st.nextToken());
            
            if (op.charAt(0) == 'I') {
                minQ.offer(val);
                maxQ.offer(val);
            } else {
                if (minQ.isEmpty())
                    continue;
                
                if (val == 1) {
                    minQ.remove(maxQ.poll());
                } else {
                    maxQ.remove(minQ.poll());
                }
            }
        }
        if (minQ.isEmpty())
            return new int[]{ 0, 0 };
        else {
            return new int[]{ maxQ.peek(), minQ.peek() };
        }
    }
}