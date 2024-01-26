package baekjoon_2752;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            l.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(l);

        StringBuilder sb = new StringBuilder();
        for (int i : l) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
