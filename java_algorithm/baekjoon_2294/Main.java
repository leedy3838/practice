package baekjoon_2294;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];
        int[] cnt = new int[K+1];

        //전체 배열을 최댓값으로 초기화
        for(int i = 1; i<=K; i++)
            cnt[i] = 100_001;

        for(int i = 0; i<N; i++)
            coin[i] = Integer.parseInt(br.readLine());

        Arrays.sort(coin);

        for(int i = 0; i<N; i++) {
            for (int j = coin[0]; j <= K; j++) {
                if (j < coin[i])
                    continue;

                cnt[j] = Math.min(cnt[j], cnt[j-coin[i]] + 1);
            }
        }

        for(int i = 1; i<=K; i++)
            if(cnt[i]==100_001)
                cnt[i] = -1;

        System.out.println(cnt[K]);
    }
}