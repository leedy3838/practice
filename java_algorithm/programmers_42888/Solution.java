package programmers_42888;

import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        
        for (String str : record) {
            StringTokenizer st = new StringTokenizer(str);
            
            String op = st.nextToken();
            String id = st.nextToken();
            
            if (op.equals("Enter")) {
                String nickname = st.nextToken();
                
                map.put(id, nickname);
            } else if (op.equals("Change") && map.containsKey(id)) {
                String nickname = st.nextToken();
                
                map.remove(id);
                map.put(id, nickname);
            }
        }
        
        List<String> answer = new ArrayList<>();
        
        for (String str : record) {
            StringTokenizer st = new StringTokenizer(str);
            
            String op = st.nextToken();
            String id = st.nextToken();
            String nickname = map.get(id);
            
            if (op.equals("Enter")) {
                answer.add(nickname + "님이 들어왔습니다.");
            } else if (op.equals("Leave")) {
                answer.add(nickname + "님이 나갔습니다.");
            }
        }
        
        return answer.toArray(String[]::new);
    }
}