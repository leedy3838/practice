package baekjoon_2631;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] intArr = new int[N+1];
        int[] LIS = new int[N+1];

        for(int i = 1; i<=N; i++)
            intArr[i] = Integer.parseInt(br.readLine());

        int maxLen = 0;
        for(int i = 1; i<=N; i++){
            int idx = lowerBound(LIS, N, intArr[i]);

            //LIS를 초기화
            LIS[idx] = intArr[i];
            //만약 마지막 자리에 수가 새로 들어갔다면 maxLen 증가
            if(idx == maxLen+1)
                maxLen++;
        }

        System.out.println(N-maxLen);
    }

    public static int lowerBound(int[] LIS, int len, int target){
        for(int i = 1; i<=len; i++){
            //만약 이전 수 중에서 더 큰 수가 없다면 끝에 배치
            if(LIS[i] == 0)
                return i;

            //이전 수 중에서 더 큰 수가 있다면 lowerBound 탐색
            if(LIS[i] >= target){
                return i;
            }
        }

        return 0;
    }
}
