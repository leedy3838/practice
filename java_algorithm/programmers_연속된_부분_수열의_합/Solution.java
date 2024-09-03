package programmers_연속된_부분_수열의_합;

import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int endIdx = Integer.MAX_VALUE;
        int startIdx = 0;

        int left = 0;
        int right = 0;
        int sum = sequence[0];

        while (right < sequence.length && left <= right) {

            if (sum < k) {
                if (++right < sequence.length)
                    sum += sequence[right];
            } else if (sum > k) {
                sum -= sequence[left++];
            } else {
                if (endIdx - startIdx > right - left) {
                    startIdx = left;
                    endIdx = right;
                }

                if (++right < sequence.length)
                    sum += sequence[right];
            }
        }

        answer[0] = startIdx;
        answer[1] = endIdx;

        return answer;
    }
}