package programmers_42842;

class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;
        
        int ans = 3;
        
        while (true) {
            if (total % ans != 0) {
                ans++;
            } else {
                int divVal = total / ans;
                
                if ((divVal - 2) * (ans - 2) == yellow) {
                    break;
                }
                
                ans++;
            }
        }
        
        return new int[]{total / ans, ans};
    }
}