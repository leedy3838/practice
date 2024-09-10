package programmers_124_나라의_숫자;

class Solution {
    public String solution(int n) {
        String answer = "";

        while (n > 0) {
            n--;

            switch (n % 3) {
                case 0:
                    answer = 1 + answer;
                    break;
                case 1:
                    answer = 2 + answer;
                    break;
                case 2:
                    answer = 4 + answer;
                    break;
            }

            n /= 3;
        }

        return answer;
    }
}