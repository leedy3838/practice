package baekjoon_1213;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int[] inputAlpha = new int[26];

        for(int i = 0; i<input.length(); i++)
            inputAlpha[input.charAt(i)-'A']++;

        int oddIdx = -1;

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i<26; i++){
            if(inputAlpha[i] == 0)
                continue;

            //알파벳의 개수가 홀수일 때
            if(inputAlpha[i]%2 == 1){
                if(oddIdx == -1) {
                    for(int j = 0; j<inputAlpha[i]/2; j++)
                        answer.append((char) ('A' + i));

                    oddIdx = i;
                }
                else{
                    System.out.println("I'm Sorry Hansoo");
                    return;
                }
            }

            //알파벳의 개수가 짝수일 때
            else{
                for(int j = 0; j<inputAlpha[i]/2; j++)
                    answer.append((char) ('A' + i));
            }
        }

        if(oddIdx != -1)
            answer.append((char) ('A' + oddIdx));

        for(int i = 25; i>=0; i--){
            if(inputAlpha[i] == 0)
                continue;

            for(int j = 0; j<inputAlpha[i]/2; j++)
                answer.append((char) ('A' + i));
        }

        System.out.println(answer);
    }
}
