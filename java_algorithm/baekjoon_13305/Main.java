package baekjoon_13305;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        //i번째 항은 i -> i+1로 가는 거리
        int[] len = new int[N-1];
        int[] money = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N-1; i++)
            len[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            money[i] = Integer.parseInt(st.nextToken());

        long sumMoney = 0;
        for(int i = 0; i<N-1; i++){
            int nowPrice = money[i];
            int buyLen = len[i];

            //현재 방문한 주유소가 나중의 주유소보다 싼 경우
            while(nowPrice < money[i+1]){
                buyLen += len[++i];

                //가야되는 거리의 끝에 도달한 경우
                if(i == len.length-1)
                    break;
            }

            sumMoney += (long)nowPrice*buyLen;
        }

        System.out.println(sumMoney);
    }
}

