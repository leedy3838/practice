package baekjoon_1339;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] charArr = new int[27];

        Map<String, Integer> map = new TreeMap<>();
        String[][] input = new String[N][];

        for(int k = 0; k<N; k++) {
            input[k] = br.readLine().split("");

            for (int i = 0; i < input[k].length / 2; i++) {
                String tmp = input[k][i];
                input[k][i] = input[k][input[k].length - 1 - i];
                input[k][input[k].length - 1 - i] = tmp;
            }

            int idx = 1;

            for (String s : input[k]) {
                int a = s.charAt(0);

                charArr[a - 65] += idx;
                idx *= 10;
            }
        }

        int num = 9;
        while(true){
            int maxNum = 0;
            int maxIdx = 0;

            for(int i = 0; i<26; i++){
                if(charArr[i] > maxNum){
                    maxNum = charArr[i];
                    maxIdx = i;
                }
            }

            if(maxNum == 0)
                break;

            charArr[maxIdx] = 0;

            String ch = Character.toString(maxIdx+65);
            map.put(ch, num);
            num--;
        }

        for(int i = 0; i<N; i++){
            int digit = 1;

            for(int j = 0; j<input[i].length; j++){
                answer += map.get(input[i][j]) * digit;
                digit *= 10;
            }
        }

        System.out.println(answer);
    }
}
