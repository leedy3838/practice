package programmers_161988;

class Solution {
    public long solution(int[] sequence) {
        long answer = sequence[0];
        
        long sum = 0;
        int mulV = 1;
        for (int seq : sequence) {
            int addV = seq * mulV;
            
            if (addV + sum < 0) {
                sum = 0;
            } else {
                sum += addV;
                answer = Math.max(answer, sum);
            }
            
            mulV *= -1;
        }
        
        sum = 0;
        mulV = -1;
        for (int seq : sequence) {
            int addV = seq * mulV;
            
            if (addV + sum < 0) {
                sum = 0;
            } else {
                sum += addV;
                answer = Math.max(answer, sum);
            }
            
            mulV *= -1;
        }
        
        return answer;
    }
}