package baekjoon_5557;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[][] memoArr = new long[N+1][21];

        st = new StringTokenizer(br.readLine());
        //처음 입력받은 수 입력
        int firstNum = Integer.parseInt(st.nextToken());
        memoArr[1][firstNum]++;

        for(int i = 2; i<N; i++){
            int num = Integer.parseInt(st.nextToken());

            for(int j = 0; j<=20; j++){
                if(memoArr[i-1][j] == 0)
                    continue;

                int plus = j+num;
                int minus = j-num;

                if(plus>=0 && plus<=20)
                    memoArr[i][plus] += memoArr[i-1][j];
                if(minus>=0 && minus<=20)
                    memoArr[i][minus] += memoArr[i-1][j];
            }
        }

        int answer = Integer.parseInt(st.nextToken());

        System.out.println(memoArr[N-1][answer]);
    }
}
