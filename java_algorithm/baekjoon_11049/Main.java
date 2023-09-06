package baekjoon_11049;

import java.io.*;
import java.util.*;
public class Main {

    static int N;
    static Node[][] rowCol;

    public static void main(String[] args) throws IOException{

        firstSetting();
        makeRowCol();

        System.out.println(rowCol[1][N].cnt);
    }

    private static void firstSetting() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        rowCol = new Node[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            rowCol[i][i] = new Node(r, c, 0);
        }
    }

    private static void makeRowCol(){

        for(int j = 1; j < N; j++) {
            for (int i = 1; i <= N; i++) {
                if(i + j > N)
                    continue;

                makeEachSquare(i, i + j);
            }
        }
    }

    private static void makeEachSquare(int row, int col){

        rowCol[row][col] = new Node(0, 0, Integer.MAX_VALUE);

        int cnt = 1;
        for(int i = row; i < col; i++){
            Node first = rowCol[row][i];
            Node second = rowCol[row + cnt++][col];

            int sum = first.rowNum * first.colNum * second.colNum + first.cnt + second.cnt;

            if(sum < rowCol[row][col].cnt)
                rowCol[row][col] = new Node(first.rowNum, second.colNum, sum);
        }
    }

    private static class Node{

        int rowNum, colNum, cnt;

        public Node(int rowNum, int colNum, int cnt){
            this.rowNum = rowNum;
            this.colNum = colNum;
            this.cnt = cnt;
        }
    }
}
