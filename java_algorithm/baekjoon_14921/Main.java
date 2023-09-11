package baekjoon_14921;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] intArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            intArr[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = N - 1;
        int ans = Integer.MAX_VALUE;

        while(left < right){

            int sum = intArr[left] + intArr[right];
            if(Math.abs(sum) < Math.abs(ans))
                ans = sum;

            if(sum < 0)
                left++;
            else if(sum > 0)
                right--;
            else
                break;
        }

        System.out.println(ans);
    }
}
