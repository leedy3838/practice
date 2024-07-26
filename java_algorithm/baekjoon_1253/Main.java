package baekjoon_1253;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] data;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        data = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data);
    }

    private static void solveProblem() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            int startIdx = 0;
            int endIdx = N - 1;

            while (startIdx < endIdx) {
                if (data[i] == data[startIdx] + data[endIdx]) {
                    if (startIdx != i && endIdx != i) {
                        count++;
                        break;
                    } else if (startIdx == i) {
                        startIdx++;
                    } else {
                        endIdx--;
                    }
                } else if (data[i] > data[startIdx] + data[endIdx]) {
                    startIdx++;
                } else if (data[i] < data[startIdx] + data[endIdx]) {
                    endIdx--;
                }
            }
        }

        System.out.println(count);
    }
}
