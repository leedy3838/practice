package programmers_12980;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        
        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n /= 2;
                answer++;
            }
        }
        
        return answer;
    }
}