package baekjoon_9252;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String inputStr = br.readLine();
        String targetStr = br.readLine();

        int N = inputStr.length();
        int M = targetStr.length();

        char[] input = new char[N + 1];
        char[] target = new char[M + 1];

        for(int i = 0; i < N; i++)
            input[i + 1] = inputStr.charAt(i);
        for(int i = 0; i < M; i++)
            target[i + 1] = targetStr.charAt(i);

        int[][] dp = new int[N+1][M+1];
        int maxVal = 0;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(input[i] == target[j]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxVal = Math.max(maxVal, dp[i][j]);
                }
                else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        Stack<Character> s = new Stack<>();

        int nowR = N;
        int nowC = M;
        while(nowR > 0 && nowC > 0){
            if(dp[nowR][nowC] == dp[nowR][nowC - 1])
                nowC--;
            else if(dp[nowR][nowC] == dp[nowR - 1][nowC])
                nowR--;
            else{
                s.push(input[nowR]);
                nowR--;
                nowC--;
            }
        }

        while(!s.isEmpty())
            sb.append(s.pop());

        System.out.println(maxVal);
        System.out.println(sb);
    }
}
