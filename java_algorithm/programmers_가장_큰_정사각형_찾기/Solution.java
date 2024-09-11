package programmers_가장_큰_정사각형_찾기;

class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        
        int R = board.length;
        int C = board[0].length;
        
        int maxSize = Math.min(R, C) - 1;
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = Math.min(board[i][j - 1], Math.min(board[i - 1][j], board[i - 1][j - 1])) + 1;
                    }
                    
                    answer = Math.max(answer, board[i][j]);
                }
            }
        }

        return answer * answer;
    }
}