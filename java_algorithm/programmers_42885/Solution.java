package programmers_42885;

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int right = people.length - 1;
        int left = 0;
        
        while (left <= right) {
            int sum = people[right--];
            
            if (sum + people[left] <= limit) {
                left++;
            }
            
            answer++;
        }
        
        return answer;
    }
}