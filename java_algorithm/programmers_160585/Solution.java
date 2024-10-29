package programmers_160585;

import java.util.*;

class Solution {
    private int N, firstCnt = 0, secondCnt = 0;
    private char[][] board;
    
    public int solution(String[] board) {
        setting(board);
        
        return solve() ? 1 : 0;
    }
    
    private void setting(String[] original) {
        N = original.length;
        
        board = new char[N][N];
        
        for (int i = 0; i < original.length; i++) {
            String or = original[i];
            
            for (int j = 0; j < or.length(); j++) {
                char ch = or.charAt(j);
                
                if (ch == 'O') {
                    board[i][j] = 'O';
                    firstCnt++;
                } else if (ch == 'X') {
                    board[i][j] = 'X';
                    secondCnt++;
                } else {
                    board[i][j] = '.';
                }
            }
        }
    }
    
    private boolean solve() {
        //개수 범위 제한
        if (firstCnt < secondCnt || firstCnt > secondCnt + 1) {
            return false;
        }
        
        boolean isFirstWin = checkWin('O');
        boolean isSecondWin = checkWin('X');
        
        if (isFirstWin && isSecondWin) {    //둘 다 이김
            return false;
        } else if (isFirstWin) {    //선공이 이김
            if (firstCnt == secondCnt) {
                return false;
            }
        } else if (isSecondWin) {   //후공이 이김
            if (firstCnt != secondCnt) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean checkWin(char ch) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != ch) {
                    break;
                }
                
                if (j == N - 1) {
                    return true;
                }
            }
            
            for (int j = 0; j < N; j++) {
                if (board[j][i] != ch) {
                    break;
                }
                
                if (j == N - 1) {
                    return true;
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
            if (board[i][i] != ch) {
                break;
            }
            
            if (i == N - 1) {
                return true;
            }
        }
        
        for (int i = 0; i < N; i++) {
            if (board[N - 1 - i][i] != ch) {
                break;
            }
            
            if (i == N - 1) {
                return true;
            }
        }
        
        return false;
    }
}