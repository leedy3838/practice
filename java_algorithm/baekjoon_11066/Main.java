package baekjoon_11066;

import java.io.*;
import java.util.*;

public class Main {

    private static final String inputNumMisMatch = "입력의 수가 맞지 않습니다.";

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        solveProblem();
    }

    private static void solveProblem(){

        StringBuilder sb = new StringBuilder();

        int T = findTestCnt();
        while(T-->0){
            int[] intArr = firstSetting();
            sb.append(findMinVal(intArr)).append("\n");
        }

        System.out.println(sb);
    }

    private static int findTestCnt(){

        int T = 0;
        try {
            T = Integer.parseInt(br.readLine());
        } catch (IOException e){
            e.printStackTrace(System.out);
        }

        return T;
    }

    private static int[] firstSetting(){

        int[] returnArr = new int[0];

        try{
            int K = Integer.parseInt(br.readLine());
            int[] inputArr = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if(Arrays.stream(inputArr).count() != K){
                throw new IOException(inputNumMisMatch);
            }

            // 0번째 항이 0인 배열 생성
            returnArr = new int[K+1];
            returnArr[0] = 0;

            // 입력 배열의 값을 복사하고 누적합 계산
            System.arraycopy(inputArr, 0, returnArr, 1, K);
            Arrays.parallelPrefix(returnArr, Integer::sum);
        } catch (IOException e){
            e.printStackTrace(System.out);
        }

        return returnArr;
    }

    private static int findMinVal(int[] sumArr){

        int K = sumArr.length;
        int[][] dp = new int[K][K];

        for(int pageCnt = 1; pageCnt < K; pageCnt++){
            for(int from = 1; from + pageCnt < K; from++){
                int to = from + pageCnt;
                dp[from][to] = Integer.MAX_VALUE;

                for(int divide = from; divide < to; divide++){
                    dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sumArr[to] - sumArr[from - 1]);
                }
            }
        }

        return dp[1][K - 1];
    }
}
