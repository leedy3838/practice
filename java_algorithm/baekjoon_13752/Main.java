package baekjoon_13752;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int cnt = Integer.parseInt(br.readLine());
            sb.append("=".repeat(cnt));
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
