package baekjoon_13019;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputA = br.readLine();
        String inputB = br.readLine();

        int[] alphaA = new int[26];
        int[] alphaB = new int[26];

        //길이가 다른 경우 A를 B로 바꾸는 것이 불가
        if(inputA.length() != inputB.length()){
            System.out.println(-1);
            return;
        }

        //각각의 알파벳이 몇 개가 있는지 확인
        for(int i = 0; i<inputA.length(); i++){
            alphaA[inputA.charAt(i)-'A']++;
            alphaB[inputB.charAt(i)-'A']++;
        }

        //알파벳의 개수가 다른 경우 A를 B로 바꾸는 것이 불가
        for(int i = 0; i<26; i++){
            if(alphaA[i] != alphaB[i]){
                System.out.println(-1);
                return;
            }
        }

        int count = 0;
        int idx = inputB.length()-1;

        //inputB의 뒤에서부터 오면서 연속적으로 있는 알파벳 중에서
        //inputA에 연속적으로 있는 알파벳이 몇 개인지 확인
        for(int i = inputB.length()-1; i>=0; i--){
            if(inputB.charAt(idx) == inputA.charAt(i)){
                count++;
                idx--;
            }
        }

        System.out.println(inputB.length()-count);
    }
}
