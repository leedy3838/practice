package baekjoon_1316;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        while(N-- > 0){
            boolean[] alphaArr = new boolean[26];
            char[] input = br.readLine().toCharArray();
            boolean isOk = true;

            for(int i = 0; i < input.length; i++){
                int alphaIdx = input[i] - 'a';

                if(alphaArr[alphaIdx] && input[i - 1] != input[i]){
                    isOk = false;
                    break;
                }

                alphaArr[alphaIdx] = true;
            }

            if(isOk)
                cnt++;
        }

        System.out.println(cnt);
    }
}
