package baekjoon_3046;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R1 = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        if (R1 > S) {
            System.out.println(S - (R1 - S));
        } else {
            System.out.println(S + (S - R1));
        }
    }
}
