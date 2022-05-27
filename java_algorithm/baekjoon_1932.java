import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] intArr = new int[n][];

        for(int i = 1; i<=n; i++)
            intArr[i-1] = new int[i];

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<i+1; j++)
                intArr[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i<n; i++){
            for(int j = 0; j<i+1; j++){
                if(j == 0)
                    intArr[i][j] += intArr[i-1][j];
                else if(j == i)
                    intArr[i][j] += intArr[i-1][j-1];
                else
                    intArr[i][j] += Math.max(intArr[i-1][j], intArr[i-1][j-1]);
            }
        }

        int max = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++)
            max = Math.max(max, intArr[n-1][i]);

        System.out.println(max);
    }
}