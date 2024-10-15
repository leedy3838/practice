package programmers_67258;

import java.util.*;

class Solution {
    private int typeCnt = 0;
    
    public int[] solution(String[] gems) {
        findGemTypeCnt(gems);
        
        return findAnswer(gems);
    }
    
    private void findGemTypeCnt(String[] gems) {
        Set<String> s = new HashSet<>();
        
        for (String gem : gems) {
            s.add(gem);
        }
        
        typeCnt = s.size();
    }
    
    private int[] findAnswer(String[] gems) {
        int left = 0, right = Integer.MAX_VALUE;
        
        Map<String, Integer> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        
        for (int i = 0; i < gems.length; i++) {
            String gem = gems[i];
            
            map.put(gem, map.getOrDefault(gem, 0) + 1);
            q.offer(new Node(i, gem));
            
            while (map.containsKey(q.peek().val) && map.get(q.peek().val) > 1) {
                Node node = q.poll();
                map.put(node.val, map.get(node.val) - 1);
            }
            
            if (map.keySet().size() == typeCnt) {
                if (i - q.peek().idx < right - left) {
                    left = q.peek().idx;
                    right = i;
                }
            }
        }
        
        return new int[]{left + 1, right + 1};
    }
    
    static class Node {
        int idx;
        String val;
        
        public Node(int idx, String val) {
            this.idx = idx;
            this.val = val;
        }
    }
}