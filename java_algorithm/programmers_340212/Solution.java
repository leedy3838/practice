package programmers_340212;

class Solution {
    long limit;
    int[] diffs, times;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.limit = limit;
        this.diffs = diffs;
        this.times = times;
        
        int left = 1, right = Integer.MAX_VALUE;
        
        while (left < right) {
            int mid = left / 2 + right / 2;
            
            if (canDo(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canDo(int level) {
        long sum = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            int time = times[i];
            
            if (diff <= level) {
                sum += time;
            } else {
                long timeSum = i == 0 ? times[i] : times[i] + times[i - 1];
     
                sum += timeSum * (diff - level) + time;
            }
        }
        
        return sum <= limit;
    }
}