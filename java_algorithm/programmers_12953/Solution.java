package programmers_12953;

class Solution {
    public int solution(int[] arr) {
        int answer = 1;
        
        for (int num : arr) {
            int gcd;
            if (num >= answer) {
                gcd = gcd(num, answer);
            } else {
                gcd = gcd(answer, num);
            }
            
            answer *= num / gcd;
        }
        
        return answer;
    }
    
    //num1 >= num2
    private int gcd(int num1, int num2) {
        
        while (num1 % num2 != 0) {
            int tmp = num1 % num2;
            num1 = num2;
            num2 = tmp;
        }
        
        return num2;
    }
}