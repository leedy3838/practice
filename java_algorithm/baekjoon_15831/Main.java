package baekjoon_15831;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, B, W;
    private static boolean[] isWhite;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //총 돌의 수
        B = Integer.parseInt(st.nextToken());   //최대 검은 돌의 수
        W = Integer.parseInt(st.nextToken());   //최소 흰 돌의 수

        isWhite = new boolean[N];

        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            isWhite[i] = input.charAt(i) == 'W';
        }
    }

    private static void solveProblem() {
        int startIdx = 0;
        int endIdx = -1;
        int containB = 0;
        int containW = 0;

        int answer = 0;

        while (endIdx < N - 1) {
            if (containB > B) {
                if (isWhite[startIdx])
                    containW--;
                else
                    containB--;

                startIdx++;
            } else {
                endIdx++;

                if (isWhite[endIdx])
                    containW++;
                else
                    containB++;
            }

            if (containW >= W && containB <= B) {
                answer = Math.max(answer, containB + containW);
            }
        }

        System.out.println(answer);
    }
}
