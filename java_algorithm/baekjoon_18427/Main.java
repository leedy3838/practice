package baekjoon_18427;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<List<Integer>> l = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            l.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens())
                l.get(i).add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < N; i++)
            Collections.sort(l.get(i));

        int[] dp = new int[H+1];
        dp[0] = 1;

        //i는 사람을 의미
        for(int i = 0; i < N; i++) {
            //j는 높이를 의미
            for (int j = H; j > 0; j--) {
                for(int k : l.get(i)){
                    if(j - k < 0)
                        break;

                    dp[j] += dp[j - k];
                    dp[j] %= 10007;
                }
            }
        }

        System.out.println(dp[H]);
    }
}
