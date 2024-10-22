package programmers_155651;

import java.util.*;

class Solution {
    
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (str1, str2) -> str1[0].compareTo(str2[0]));
        Queue<Integer> q = new PriorityQueue<>();
        
        for (String[] book : book_time) {
            int startTime = parseTime(book[0]);
            int endTime = parseTime(book[1]);
            
            if (q.isEmpty()) {
                q.offer(endTime + 10);
            } else {
                if (q.peek() <= startTime) {
                    q.poll();
                }
                
                q.add(endTime + 10);
            }
        }
        
        return q.size();
    }
    
    private int parseTime(String book) {
        StringTokenizer st = new StringTokenizer(book, ":");
        
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        
        return hour * 60 + minute;
    }
}