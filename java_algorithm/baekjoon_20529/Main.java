package baekjoon_20529;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());

            String[] input = br.readLine().split(" ");

            if(N > 32){
                sb.append(0).append("\n");
                continue;
            }

            int minSum = Integer.MAX_VALUE;

            for(int i = 0; i < N; i++){
                for(int j = i + 1; j < N; j++){
                    for(int k = j + 1; k < N; k++){
                        int nowSum = 0;

                        for(int l = 0; l < 4; l++){
                            if(input[i].charAt(l) != input[j].charAt(l))
                                nowSum++;
                            if(input[i].charAt(l) != input[k].charAt(l))
                                nowSum++;
                            if(input[j].charAt(l) != input[k].charAt(l))
                                nowSum++;
                        }

                        minSum = Math.min(minSum, nowSum);
                    }
                }
            }

            sb.append(minSum).append("\n");
        }

        System.out.print(sb);
    }
}
