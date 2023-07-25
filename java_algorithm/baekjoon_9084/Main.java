package baekjoon_9084;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            int N = Integer.parseInt(br.readLine());

            /*//1번부터 입력 받기 시작
            int[] coins = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i<=N; i++)
                coins[i] = Integer.parseInt(st.nextToken());*/

            //인터넷 참고 풀이
            int[] coins = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++)
                coins[i] = Integer.parseInt(st.nextToken());

            int M = Integer.parseInt(br.readLine());

            int[] dp = new int[M+1];
            dp[0] = 1;

            for(int coin : coins){
                for(int i = coin; i<=M; i++)
                    dp[i] += dp[i - coin];
            }

            sb.append(dp[M]).append("\n");
/* 내가 푼 풀이
            //row는 동전을 몇 번 동전까지 쓸 것인지
            //column은 돈의 액수
            //value는 횟수의 최대값
            int[][] dp = new int[N+1][M+1];

            for(int coin = 1; coin<=N; coin++){
                for(int nowMoney = 1; nowMoney<=M; nowMoney++){
                    //새로운 동전만 써서 금액을 만드는 경우
                    if(nowMoney%coins[coin] == 0)
                        dp[coin][nowMoney]++;

                    if(coin != 1) {
                        //이전 동전까지만 쓰고 새 동전을 1개만 써서 현재 금액을 만들 수 있는 경우
                        dp[coin][nowMoney] += dp[coin - 1][nowMoney];

                        //새로운 동전을 함께 써서 금액을 만드는 경우
                        if (nowMoney-coins[coin] > 0) {
                            dp[coin][nowMoney] += dp[coin][nowMoney - coins[coin]];

                            //중복된 경우
                            if((nowMoney-coins[coin])%coins[coin] == 0)
                                dp[coin][nowMoney]--;
                        }
                    }
                }
            }

            sb.append(dp[N][M]).append("\n");*/
        }

        System.out.print(sb);
    }
}
