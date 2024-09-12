package programmers_다음_큰_숫자;

class Solution {
    public int solution(int n) {
        int answer = n + 1;
        
        int bitCnt = Integer.bitCount(n);
        
        while (true) {
            if (Integer.bitCount(answer) == bitCnt)
                break;
            else
                answer++;
        }
        
        return answer;
    }
}