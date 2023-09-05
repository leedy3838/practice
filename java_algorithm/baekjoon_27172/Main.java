package baekjoon_27172;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] intArr = new int[N + 1];
        int maxNum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            intArr[i] = Integer.parseInt(st.nextToken());

            maxNum = Math.max(maxNum, intArr[i]);
        }

        //에라토스테네스의 체 이용
        //sifter의 index는 intArr의 값, value는 intArr의 index
        int[] sifter = new int[maxNum + 1];

        for(int i = 1; i <= N; i++){
            sifter[intArr[i]] = i;
        }

        int[] ans = new int[N + 1];

        for(int index = 1; index <= N; index++){
            for(int val = intArr[index] * 2; val <= maxNum; val += intArr[index]){
                //나눴을 때 0이 아닌 경우
                if(sifter[val] != 0){
                    ans[index]++;
                    ans[sifter[val]]--;
                }
            }
        }

        for(int i = 1; i <= N; i++)
            System.out.print(ans[i] + " ");
        System.out.println();
    }
}
