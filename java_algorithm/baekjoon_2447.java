import java.io.*;

public class Main{
    static char[][] array;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        array = new char[N][N];

        recursion(0, 0, N, true);

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++)
                sb.append(array[i][j]);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void recursion(int row, int col, int N, boolean isStar){
        int count = 0;

        if(N == 1){
            if(isStar)
                array[row][col] = '*';
            else
                array[row][col] = ' ';
            return;
        }
 
        for(int i = row; i<row+N; i += N/3){
            for(int j = col; j<col+N; j += N/3){
                if(++count != 5 && isStar)
                    recursion(i, j,N/3,true);
                else
                    recursion(i, j, N/3, false);
            }
        }
    }
}
