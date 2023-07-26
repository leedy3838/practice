package baekjoon_5582;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1번 index부터 입력을 넣기 위해서 사용
        //입력은 대문자만 주어짐
        String input1 = 'a'+br.readLine();
        String input2 = 'b'+br.readLine();

        int[][] dp = new int[input1.length()][input2.length()];

        int max = 0;

        for(int i = 1; i<input1.length(); i++){
            for(int j = 1; j<input2.length(); j++){
                if(input1.charAt(i) == input2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max);
    }
}
