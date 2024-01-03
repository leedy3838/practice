package baekjoon_12738;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        solveProblem();
    }

    private static void solveProblem() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> l = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int val = Integer.parseInt(st.nextToken());

            if (l.isEmpty() || l.get(l.size() - 1) < val)
                l.add(val);
            else {
                int idx = binarySearch(val, l);
                l.set(idx, val);
            }
        }

        System.out.println(l.size());
    }

    //lower bound
    private static int binarySearch(int val, List<? extends Integer> l) {

        int left = 0;
        int right = l.size() - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (l.get(mid) < val)
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }
}
