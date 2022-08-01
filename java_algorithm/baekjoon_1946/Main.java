package baekjoon_1946;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            int N = Integer.parseInt(br.readLine());

            int[] intArr = new int[N+1];

            for(int i = 1; i<=N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                int text = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());

                intArr[text] = interview;
            }

            int cnt = 1;
            int standard = intArr[1];

            for(int i = 2; i<=N; i++){
                int interview = intArr[i];

                if(standard > interview){
                    cnt++;
                    standard = interview;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }
}
