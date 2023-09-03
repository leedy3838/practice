package baekjoon_2467;

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

        int leftIdx = 0;
        int rightIdx = N-1;

        int leftAnsV = intArr[0];
        int rightAnsV = intArr[N-1];
        int lastMinSum = leftAnsV + rightAnsV;

        while(leftIdx != rightIdx){

            if(Math.abs(intArr[leftIdx] + intArr[rightIdx]) < Math.abs(lastMinSum)){
                leftAnsV = intArr[leftIdx];
                rightAnsV = intArr[rightIdx];
                lastMinSum = intArr[leftIdx] + intArr[rightIdx];
            }

            if(intArr[leftIdx] + intArr[rightIdx] < 0)
                leftIdx++;
            else
                rightIdx--;
        }

        System.out.println(leftAnsV + " " + rightAnsV);
    }
}
