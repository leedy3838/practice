package baekjoon_12931;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] intArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            intArr[i] = Integer.parseInt(st.nextToken());

        System.out.println(calc(intArr));
    }

    static int calc(int[] intArr){
        int count = 0;
        boolean isAllZero = false;

        while(!isAllZero){
            isAllZero = true;

            for(int i = 0; i<intArr.length; i++){
                if(intArr[i]%2 == 1){
                    intArr[i] -= 1;

                    count++;
                    isAllZero = false;
                    continue;
                }
                else if(intArr[i] != 0)
                    isAllZero = false;

                if(i == intArr.length-1 && !isAllZero){
                    boolean allZero = true;

                    for(int j = 0; j < intArr.length; j++) {
                        if(intArr[j] != 0) {
                            intArr[j] /= 2;
                            allZero = false;
                        }
                    }

                    if(!allZero)
                        count++;
                    if(allZero)
                        isAllZero = true;
                }
            }
        }

        return count;
    }
}
