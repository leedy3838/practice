package baekjoon_12852;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1]; //최소 횟수 저장
        int[] before = new int[N + 1]; //경로 저장

        StringBuilder str = new StringBuilder();

        dp[1] = 0;

        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            before[i] = i - 1;

            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                before[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                before[i] = i / 2;
            }
        }
        sb.append(dp[N]).append("\n");

        while(N > 0){
            str.append(N).append(" ");
            N = before[N];
        }

        sb.append(str);

        System.out.println(sb);
    }
}
