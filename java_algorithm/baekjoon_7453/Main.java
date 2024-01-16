package baekjoon_7453;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[][] data;

    public static void main(String[] args) {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st;

            N = Integer.parseInt(br.readLine());
            data = new int[N][4];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 4; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void solveProblem() {

        int[] ABData = new int[N * N];
        int[] CDData = new int[N * N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ABData[idx] = data[i][0] + data[j][1];    //A, B 데이터의 합
                CDData[idx] = data[i][2] + data[j][3];    //C, D 데이터의 합
                idx++;
            }
        }

        Arrays.sort(ABData);
        Arrays.sort(CDData);

        int left = 0;
        int right = N * N - 1;
        long count = 0;

        while (left < N * N && right >= 0) {
            int leftV = ABData[left];
            int rightV = CDData[right];

            if (leftV + rightV < 0) {
                left++;
            } else if (leftV + rightV > 0) {
                right--;
            } else {
                int leftEqualCase = 0;
                int rightEqualCase = 0;

                //같은 수가 중복으로 들어가 있는 경우
                while (left < N * N && ABData[left] == leftV) {
                    left++;
                    leftEqualCase++;
                }

                while (right >= 0 && CDData[right] == rightV) {
                    right--;
                    rightEqualCase++;
                }

                count += (long) leftEqualCase * rightEqualCase;
            }
        }

        System.out.println(count);
    }
}
