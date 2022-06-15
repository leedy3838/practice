package baekjoon_2293;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coin = new int[N+1];
        int[] cnt = new int[K+1];
        cnt[0] = 1;

        for(int i = 1; i<=N; i++)
            coin[i] = Integer.parseInt(br.readLine());

        //coin[] 을 전부 순회하면서 cnt를 더해줌
        for(int i = 1; i<=N; i++){
            //j는 현재 가질 수 있는 가치의 총량을 의미
            for(int j = coin[0]; j<=K; j++){
                if(j<coin[i])
                    continue;

                cnt[j] += cnt[j-coin[i]];
            }
        }

        System.out.println(cnt[K]);
    }
}
