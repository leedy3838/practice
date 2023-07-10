package baekjoon_12970;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //true이면 'A', false이면 'B'를 의미
        boolean[] strArr = new boolean[N+1];

        //조건을 만족하는 쌍의 개수
        int count = 0;
        //'A'가 지금까지 몇 개가 들어가 있는지
        int aCount = 0;
        boolean isOK = false;

        for(int i = 1; i<=N; i++){
            //만약 현재의 index에 'A'가 들어갔을 때의 count
            int nowCount = count+(N-i)-aCount;

            //현재 자리에 'A'를 넣어 줌
            if(nowCount < K) {
                strArr[i] = true;
                aCount++;
                count = nowCount;
            }
            else if(nowCount == K){
                strArr[i] = true;
                isOK = true;
                break;
            }
        }

        if(isOK){
            for(int i = 1; i<=N; i++){
                if(strArr[i])
                    System.out.print('A');
                else
                    System.out.print('B');
            }

            System.out.println();
        }
        else
            System.out.println(-1);
    }
}
