package baekjoon_1464;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split("");
        //이전에 문자열이 뒤집혔는지를 확인
        boolean isFlipped = false;

        for(int i = 0; i<input.length; i++){
            if(isFlipped){
                isFlipped = false;

                //지금까지 입력받은 문자열을 뒤집기
                for(int j = 0; j<(i+1)/2; j++){
                    String temp = input[j];
                    input[j] = input[i-j];
                    input[i-j] = temp;
                }
            }

            if(i == input.length-1)
                break;

            //앞의 알파벳의 사전순이 더 뒤에 있을 때
            //또한 맨 앞의 알파벳이 다음의 알파벳이 앞에 있지 않을 때
            //뒤집기
            if(input[i].compareTo(input[i+1])>0 && input[0].compareTo(input[i+1])>=0){
                isFlipped = true;

                //지금까지 입력받은 문자열을 뒤집기
                for(int j = 0; j<(i+1)/2; j++){
                    String temp = input[j];
                    input[j] = input[i-j];
                    input[i-j] = temp;
                }
            }
        }

        for(String string : input)
            System.out.print(string);
        System.out.println();
    }
}
