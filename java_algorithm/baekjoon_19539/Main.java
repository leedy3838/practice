package baekjoon_19539;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int num = Integer.parseInt(br.readLine());

        int oddCnt = 0;
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<num; i++){
            int n = Integer.parseInt(st.nextToken());

            sum += n;
            oddCnt += n%2;
        }

        if(sum%3==0 && oddCnt<=sum/3)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
