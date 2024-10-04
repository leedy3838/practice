package programmers_64064;

import java.util.*;

class Solution {
    private String[] userId, bannedId;
    private int userLen, banLen;
    
    private Set<Set<String>> resultSet;
    private boolean[] isVisited;

    public int solution(String[] user_id, String[] banned_id) {
        userId = user_id;
        bannedId = banned_id;
        
        userLen = userId.length;
        banLen = bannedId.length;
        
        resultSet = new HashSet<>();
        isVisited = new boolean[userLen];
        
        findCombinations(0, new HashSet<>());

        return resultSet.size();
    }
    
    private void findCombinations(int banIdx, Set<String> currentSet) {
        if (banIdx == banLen) {
            resultSet.add(new HashSet<>(currentSet));
            return;
        }
        
        for (int i = 0; i < userLen; i++) {
            if (isVisited[i]) continue;
            
            if (matchStr(userId[i], bannedId[banIdx])) {
                isVisited[i] = true;
                currentSet.add(userId[i]);
                
                findCombinations(banIdx + 1, currentSet);
                
                currentSet.remove(userId[i]);
                isVisited[i] = false;
            }
        }
    }
    
    private boolean matchStr(String userStr, String banStr) {
        if (userStr.length() != banStr.length()) {
            return false;
        }
        
        for (int i = 0; i < userStr.length(); i++) {
            if (banStr.charAt(i) != userStr.charAt(i) && banStr.charAt(i) != '*') {
                return false;
            }
        }
        
        return true;
    }
}
