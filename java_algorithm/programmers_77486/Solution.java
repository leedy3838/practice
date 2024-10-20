package programmers_77486;

import java.util.*;

class Solution {
    private int N;
    private Map<String, Integer> map = new HashMap<>();
    private int[] parentIdx;
    private int[] result;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        N = enroll.length;
        
        setting(enroll, referral);
        solve(seller, amount);
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            answer.add(result[i]);
        }
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private void setting(String[] enroll, String[] referral) {
        parentIdx = new int[N + 1];
        result = new int[N + 1];
        
        int count = 0;
        map.put("-", count++);
        for (String person : enroll) {
            map.put(person, count++);
        }
        
        parentIdx[0] = -1;
        for (int i = 0; i < N; i++) {
            int parent = map.get(referral[i]);
            parentIdx[i + 1] = parent;  //0번 idx는 root
        }
    }
    
    private void solve(String[] seller, int[] amount) {
        for (int i = 0; i < seller.length; i++) {
            int sellerIdx = map.get(seller[i]);
            int money = amount[i] * 100;
            
            result[sellerIdx] += money;
            
            int tossMoney = money / 10;
            while (parentIdx[sellerIdx] != -1) {
                result[sellerIdx] -= tossMoney;
                
                result[parentIdx[sellerIdx]] += tossMoney;
                
                tossMoney /= 10;
                sellerIdx = parentIdx[sellerIdx];
            }
        }
    }
}