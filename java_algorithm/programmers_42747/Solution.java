package programmers_42747;

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int length = citations.length;

        Arrays.sort(citations);

        for (int i = length - 1; i >= 0; i--){
            if (citations[i] >= length - i) {
                answer = length - i;
            }
        };

        return answer;
    }
}