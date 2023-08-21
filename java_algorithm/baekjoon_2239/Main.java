package baekjoon_2239;

import java.io.*;
public class Main {

    private static final int[][] sudoku = new int[9][9];
    private static boolean isPerfect = false;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 9; i++){
            String[] input = br.readLine().split("");

            for(int j = 0; j < 9; j++)
                sudoku[i][j] = Integer.parseInt(input[j]);
        }

        playSudoku(0);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++)
                sb.append(sudoku[i][j]);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void playSudoku(int idx){

        if(idx == 81){
            isPerfect = true;
            return;
        }

        int row = idx / 9;
        int col = idx % 9;

        if(sudoku[row][col] != 0){
            playSudoku(idx + 1);
            return;
        }

        for(int i = 1; i <= 9; i++){
            if(!isPossible(row, col, i))
                continue;

            sudoku[row][col] = i;
            playSudoku(idx + 1);

            if(isPerfect)
                return;

            sudoku[row][col] = 0;
        }
    }

    private static boolean isPossible(int row, int col, int num){

        for(int i = 0; i < 9; i++) {
            if(sudoku[row][i] == num)
                return false;
            if(sudoku[i][col] == num)
                return false;
        }

        int startR = row / 3 * 3;
        int startC = col / 3 * 3;

        for(int i = startR; i < startR + 3; i++){
            for(int j = startC; j < startC + 3; j++){
                if(sudoku[i][j] == num)
                    return false;
            }
        }

        return true;
    }
}
