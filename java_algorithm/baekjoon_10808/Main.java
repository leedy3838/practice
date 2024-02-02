package baekjoon_10808;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int[] cnt = new int[26];

        for (int i = 0; i < input.length(); i++) {
            cnt[input.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            System.out.print(cnt[i] + " ");
        }
    }
}
