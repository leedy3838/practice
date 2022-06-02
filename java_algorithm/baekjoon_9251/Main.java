package baekjoon_9251;

import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();

        int firstN = first.length();
        int secondN = second.length();

        int[][] lcs = new int[firstN+1][secondN+1];

        char[] firstArr = first.toCharArray();
        char[] secondArr = second.toCharArray();

        for(int i = 1; i<=firstN; i++){
            for(int j = 1; j<=secondN; j++){
                if(firstArr[i-1] == secondArr[j-1])
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                else
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
            }
        }

        System.out.println(lcs[firstN][secondN]);
    }
}