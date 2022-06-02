import java.util.*;
import java.io.*;

public class Main{
    static LinkedList<RowCol> ll = new LinkedList<RowCol>();
    static int[] drow = {1, -1, 0, 0};
    static int[] dcol = {0, 0, 1, -1};
    static int[][] intArr;
    static boolean[][] gone;
    static int Max = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int row = Integer.parseInt(input[0]);
        int col = Integer.parseInt(input[1]);
        intArr = new int[row][col];
        gone = new boolean[row][col];

        for(int i = 0; i<row; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<col; j++)
                intArr[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                gone[i][j] = true;
                ll.offer(new RowCol(i, j));

                dfs(i, j, intArr[i][j]);

                ll.pollLast();
                gone[i][j] = false;

                exception(i, j);
            }
        }

        System.out.print(Max);
    }

//좌표를 건네받아서 push, len(길이)가 4가 되면 Max와 비교(ㅗㅜㅏㅓ 제외)
    public static void dfs(int row, int col, int sum){
        if(ll.size() == 4){
            if(sum>Max)
                Max = sum;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int dr = row + drow[i];
            int dc = col + dcol[i];

            if (dr < 0 || dr >= intArr.length || dc < 0 || dc >= intArr[0].length)
                continue;
 
            if (!gone[dr][dc]) {
                gone[row][col] = true;
                ll.offer(new RowCol(row, col));

                dfs(dr, dc, sum + intArr[dr][dc]);

                gone[dr][dc] = false;
                ll.pollLast();
            }
        }
    }

    public static void exception(int row, int col) {
        int wing = 4;
        int min = Integer.MAX_VALUE;
        int sum = intArr[row][col];
        for (int i = 0; i < 4; i++) {
            int dr = row + drow[i];
            int dc = col + dcol[i];

            if (wing <= 2)
                return;
            if (dr < 0 || dc < 0 || dr >= intArr.length || dc >= intArr[0].length) {
                wing--;
                continue;
            }
            min = Math.min(min, intArr[dr][dc]);
            sum = sum + intArr[dr][dc];
        }

        if (wing == 4) {
            sum = sum - min;
        }
        Max = Math.max(Max, sum);
    }
}

class RowCol{
    int row, col;
    RowCol(int row,int  col){
        this.row = row;
        this.col = col;
    }
}
