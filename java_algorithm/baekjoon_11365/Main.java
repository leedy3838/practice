package baekjoon_11365;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        while (true) {
            String input = br.readLine();
            if (input.equals("END")) {
                break;
            }

            result.append(reverseString(input)).append("\n");
        }

        System.out.println(result.toString());
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}

