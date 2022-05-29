import java.io.*;
import java.math.BigInteger;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        BigInteger[][] bi = new BigInteger[101][101];

        for(int i = 1; i<=n; i++){
            for(int j = 0; j<=i; j++){

                if(j==0||j == i)
                    bi[i][j] = BigInteger.ONE;
                else{
                    bi[i][j] = bi[i-1][j].add(bi[i-1][j-1]);
                }
            }
        }

        System.out.println(bi[n][m]);
    }
}