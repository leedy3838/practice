package baekjoon_11062;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        solveProblem();
    }

    private static void solveProblem() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[] data = new int[N + 1];
            //row가 start, col이 end 카드를 의미
            //row 부터 col까지 카드가 남았을 때 선택할 수 있는 최대 값을 dp에 저장
            int[][] dp = new int[N + 2][N + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++)
                data[i] = Integer.parseInt(st.nextToken());

            //카드가 짝수 개면 마지막 카드는 player B가 가져감
            boolean turnA = (N % 2 == 1);

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j + i - 1 <= N; j++) {
                    int left = j;
                    int right = j + i - 1;

                    if (turnA) {        //카드의 개수가 홀수 개면 이전의 상황 + 이번 카드의 값이 큰 것을 선택
                        //왼쪽 카드를 골랐을 때, 오른쪽 카드를 골랐을 때 중에서 큰 값 선택
                        dp[left][right] = Math.max(dp[left + 1][right] + data[left], dp[left][right - 1] + data[right]);
                    } else {            //B 차례에 A가 더 작은 카드를 뽑게 해야 하므로, 왼쪽 카드를 뽑았을 때와 오른쪽 카드를 뽑았을 때 중, 숫자의 합이 작도록 설정
                        dp[left][right] = Math.min(dp[left + 1][right], dp[left][right - 1]);
                    }
                }

                turnA = !turnA;
            }

            sb.append(dp[1][N]).append("\n");
        }

        System.out.println(sb);
    }
}
