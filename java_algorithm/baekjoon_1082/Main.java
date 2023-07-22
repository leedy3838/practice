package baekjoon_1082;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] numPrice = new int[N];

        int minVal = 51;
        int minIdx = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            numPrice[i] = Integer.parseInt(st.nextToken());

            if(minVal > numPrice[i]){
                minIdx = i;
                minVal = numPrice[i];
            }
        }

        int M = Integer.parseInt(br.readLine());

        int[] answer = new int[M/minVal];
        int nowMoney = M%minVal;

        boolean firstIsZero  = minIdx == 0;

        for(int i = 0; i<answer.length; i++){
            nowMoney += minVal;

            for(int j = N-1; j>=minIdx; j--){
                if(firstIsZero && j == minIdx) {
                    continue;
                }

                if(nowMoney >= numPrice[j]){
                    nowMoney -= numPrice[j];
                    answer[i] = j;

                    if(firstIsZero)
                        firstIsZero = false;

                    break;
                }
            }
        }

        boolean isZero = true;
        for(int i : answer) {
            if(i != 0)
                isZero = false;

            if(!isZero)
                System.out.print(i);
        }
        if(!isZero)
            System.out.println();

        if(isZero)
            System.out.println(0);
    }
}