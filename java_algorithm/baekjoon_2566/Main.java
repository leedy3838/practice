package baekjoon_2566;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int maxVal = Integer.MIN_VALUE;
        int maxR = 0, maxC = 0;

        for(int i = 1; i < 10; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j < 10; j++){
                int num = Integer.parseInt(st.nextToken());

                if(maxVal < num){
                    maxVal = num;
                    maxR = i;
                    maxC = j;
                }
            }
        }

        System.out.println(maxVal);
        System.out.println(maxR + " " + maxC);
    }
}
