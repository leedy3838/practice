package baekjoon_21758;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] sumArr = new int[N+1];
        int[] valArr = new int[N+1];

        for(int i = 1; i<=N; i++){
            int nextNum = Integer.parseInt(st.nextToken());

            valArr[i] = nextNum;
            sumArr[i] = sumArr[i-1] + valArr[i];

        }

        int maxVal = 0;

        //벌 벌 벌집
        for(int i = 2; i<N; i++)
            maxVal = Math.max(maxVal, sumArr[N] - valArr[1] - valArr[i] + sumArr[N] - sumArr[i]);

        //벌집 벌 벌
        for(int i = 2; i<N; i++)
            maxVal = Math.max(maxVal, sumArr[i-1] + sumArr[N-1] - valArr[i]);

        //벌 벌집 벌
        for(int i = 2; i<N; i++)
            maxVal = Math.max(maxVal, sumArr[i] - valArr[1] + sumArr[N-1] - sumArr[i-1]);

        System.out.println(maxVal);
    }
}
