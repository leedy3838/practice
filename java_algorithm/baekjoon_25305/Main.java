package baekjoon_25305;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Integer[] intArr = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);

        System.out.println(intArr[k-1]);
    }
}
