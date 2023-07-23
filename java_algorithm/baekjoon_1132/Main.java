package baekjoon_1132;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        //idx는 A~j 각각의 알파벳을 의미
        long[] numArr = new long[10];

        //각각의 알파벳이 첫 자리에 온 적이 있는지
        boolean[] isFirst = new boolean[10];

        //입력을 모아두는 배열
        String[] input = new String[N];

        for(int i = 0; i<N; i++){
            input[i] = br.readLine();

            for(int j = 0; j<input[i].length(); j++) {
                int nowAlpha = input[i].charAt(j) - 'A';
                int nowLen = input[i].length()-j;

                numArr[nowAlpha] += Math.pow(10, nowLen-1);

                if(j == 0)
                    isFirst[nowAlpha] = true;
            }
        }

        //0번이 숫자 9를 의미
        List<Integer> l = new ArrayList<>();
        boolean[] isUsed = new boolean[10];

        for(int i = 0; i<10; i++){
            long maxVal = Integer.MIN_VALUE;
            int maxIdx = -1;

            for(int j = 0; j<10; j++){
                if(isUsed[j])
                    continue;

                if(numArr[j] > maxVal){
                    maxVal = numArr[j];
                    maxIdx = j;
                }
            }

            if(maxVal == 0)
                break;

            //0을 배정받고 처음 온 적이 있는 경우
            if(i == 9 && isFirst[maxIdx]){
                int idx = i-1;

                while(isFirst[l.get(idx)])
                    idx--;

                int removeNum = l.remove(idx);

                l.add(maxIdx);
                l.add(removeNum);
            }
            else
                l.add(maxIdx);

            isUsed[maxIdx] = true;
        }

        int[] calNum = new int[10];
        int val = 9;
        while(!l.isEmpty()){
            calNum[l.remove(0)] = val--;
        }

        long ans = 0;
        for(int i = 0; i<N; i++){
            String str = input[i];

            for(int j = 0; j<str.length(); j++){
                int alphaIdx = str.charAt(j) - 'A';
                int len = str.length()-j-1;

                ans += calNum[alphaIdx] * Math.pow(10, len);
            }
        }

        System.out.println(ans);
    }
}
