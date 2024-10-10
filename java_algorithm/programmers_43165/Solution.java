package programmers_43165;

class Solution {
    int[] numbers;
    int answer = 0, target;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        findAnswer(0, 0);
        
        return answer;
    }
    
    private void findAnswer(int index, int sum) {
        if (index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            
            return;
        }
        
        findAnswer(index + 1, sum + numbers[index]);
        findAnswer(index + 1, sum - numbers[index]);
    }
}