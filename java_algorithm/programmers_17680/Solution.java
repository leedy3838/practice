package programmers_17680;

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        List<String> cache = new ArrayList<>();
        
        for (String city : cities) {
            city = city.toLowerCase();
            
            if (cache.contains(city)) {
                answer++;
                
                cache.remove(city);
                cache.add(city);
            } else {
                answer += 5;
                
                cache.add(city);
                
                if (cache.size() > cacheSize) {
                    cache.remove(0);
                }
            }
        }
        
        return answer;
    }
}