package baekjoon_1497;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, minGuitarCount = Integer.MAX_VALUE;
    static int maxBitCnt = 0;
    static long[] guitarBit;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        guitarBit = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            char[] guitar = st.nextToken().toCharArray();

            for (int j = 0; j < M; j++) {
                if (guitar[j] == 'Y') {
                    guitarBit[i] |= (1L << j);
                }
            }
        }
    }

    private static void solveProblem() {
        search(0, 0L, 0);

        if (minGuitarCount == 0) {
            minGuitarCount = -1;
        }

        System.out.println(minGuitarCount);
    }

    static void search(int idx, long guitarMask, int guitarCnt) {
        int bitCount = Long.bitCount(guitarMask);

        if (bitCount == maxBitCnt && guitarCnt < minGuitarCount) {
            minGuitarCount = guitarCnt;
        }
        if (bitCount > maxBitCnt) {
            minGuitarCount = guitarCnt;
            maxBitCnt = bitCount;
        }
        if (bitCount == M || idx == N) {
            return;
        }

        //현재 기타 사용
        search(idx + 1, guitarMask | guitarBit[idx], guitarCnt + 1);
        //현재 기타 사용 X
        search(idx + 1, guitarMask, guitarCnt);
    }
}
