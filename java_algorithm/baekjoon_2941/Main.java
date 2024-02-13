package baekjoon_2941;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int len = input.length();
        int count = 0;

        for (int i = 0; i < len; i++) {
            char ch = input.charAt(i);

            if (ch == 'c' && i < len - 1) {
                if (input.charAt(i + 1) == '=' || input.charAt(i + 1) == '-') {
                    i++;
                }
            } else if (ch == 'd' && i < len - 1) {
                if (input.charAt(i + 1) == '-') {
                    i++;
                } else if (input.charAt(i + 1) == 'z' && i < len - 2) {
                    if (input.charAt(i + 2) == '=') {
                        i += 2;
                    }
                }
            } else if ((ch == 'l' || ch == 'n') && i < len - 1) {
                if (input.charAt(i + 1) == 'j') {
                    i++;
                }
            } else if ((ch == 's' || ch == 'z') && i < len - 1) {
                if (input.charAt(i + 1) == '=') {
                    i++;
                }
            }

            count++;

        }

        System.out.println(count);
    }
}