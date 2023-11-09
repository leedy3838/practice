package baekjoon_9086;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){

            String[] input = br.readLine().split("");
            sb.append(input[0]).append(input[input.length-1]).append("\n");
        }

        System.out.print(sb);
    }
}
