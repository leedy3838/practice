package programmers_12927;

class Solution {
    public long solution(int n, int[] works) {
        long[] tmp = new long[50_001];
        
        int max = 0;
        for (int work : works) {
            tmp[work]++;
            max = Math.max(max, work);
        }
        
        for (int i = max; i > 0; i--) {
            if (tmp[i] >= n) {
                tmp[i] -= n;
                tmp[i - 1] += n;
                break;
            } else {
                tmp[i - 1] += tmp[i];
                n -= tmp[i];
                tmp[i] = 0;
                
                max--;
            }
        }
        
        long answer = 0;
        
        for (int i = max; i > 0; i--) {
            if (tmp[i] != 0)
                answer += i * i * tmp[i];
        }
        
        return answer;
    }
}