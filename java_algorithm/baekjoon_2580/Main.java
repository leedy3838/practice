package baekjoon_2580;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];

        for(int i = 0; i<9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<9; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        sudoku(0, 0);
    }

    static void sudoku(int row, int col){
        if(col == 9){
            sudoku(row+1, 0);
            return;
        }

        if(row == 9){
            for(int i = 0; i<9; i++){
                for(int j = 0; j<9; j++)
                    System.out.print(board[i][j]+" ");
                System.out.println();
            }

            System.exit(0);
        }
        if(board[row][col] == 0){
            for(int i = 1; i<=9; i++){
                if(check(row, col, i)){
                    board[row][col] = i;
                    sudoku(row, col + 1);
                }
            }
            board[row][col] = 0;
            return;
        }

        sudoku(row, col+1);
    }

    static boolean check(int row, int col, int val){
        //가로 방향 탐색
        for(int i = 0; i<9; i++){
            if(board[row][i] == val)
                return false;
        }
        //세로 방향 탐색
        for(int i = 0; i<9; i++){
            if(board[i][col] == val)
                return false;
        }
        //3x3 박스 안 탐색
        int rowStart = (row/3)*3;
        int colStart = (col/3)*3;

        for(int i = rowStart; i<rowStart+3; i++){
            for(int j = colStart; j<colStart+3; j++)
                if(board[i][j] == val)
                    return false;
        }

        return true;
    }
}