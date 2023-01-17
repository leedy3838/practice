package baekjoon_2262;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        LinkedList<Integer> l = new LinkedList<>();
        int answer = 0;

        for(int i = 0; i<N; i++)
            l.add(Integer.parseInt(st.nextToken()));

        int max = N;
        while(max != 1) {
            int valIdx = l.indexOf(max);

            if (valIdx == l.size() - 1) {
                valIdx--;
            }
            else if (valIdx != 0) {
                if (l.get(valIdx - 1) > l.get(valIdx + 1)) {
                    valIdx--;
                }
            }
            int val1 = l.remove(valIdx);
            int val2 = l.remove(valIdx);

            answer += Math.abs(val1 - val2);
            l.add(valIdx, Math.min(val1, val2));
            max--;
        }

        System.out.println(answer);
    }
}
