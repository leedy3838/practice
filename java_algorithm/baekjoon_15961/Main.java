package baekjoon_15961;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int k;
    private static int c;
    private static int[] data, containNum;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());       //접시의 총 수
        int d = Integer.parseInt(st.nextToken());   //초밥의 가짓수
        k = Integer.parseInt(st.nextToken());       //연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken());       //쿠폰 번호

        data = new int[N];
        containNum = new int[d + 1];    //현재 윈도우 안에 있는 초밥의 종류(key) + 수(value)

        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solveProblem() {
        int startIdx = 1;
        int endIdx = 0;
        Set<Integer> maxSet = new HashSet<>();
        Set<Integer> nowSet = new HashSet<>();

        for (int i = 0; i < k; i++) {   //window 크기 k로 만들기
            containNum[data[++endIdx]]++;

            maxSet.add(data[endIdx]);
            nowSet.add(data[endIdx]);
        }

        while (startIdx != 0) {
            if (nowSet.size() == k && containNum[c] == 0) {
                break;
            }

            endIdx = (endIdx + 1) % N;
            if (containNum[data[endIdx]]++ == 0) {  //현재 윈도우에 없는 종류
                nowSet.add(data[endIdx]);
            }

            if (--containNum[data[startIdx]] == 0) {
                nowSet.remove(data[startIdx]);
            }
            startIdx = (startIdx + 1) % N;

            if (nowSet.size() > maxSet.size() || ((nowSet.size() == maxSet.size()) && containNum[c] == 0)) {
                maxSet.clear();
                maxSet.addAll(nowSet);
            }
        }

        maxSet.add(c);

        System.out.println(maxSet.size());
    }
}
