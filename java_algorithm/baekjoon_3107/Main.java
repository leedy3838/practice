package baekjoon_3107;

import java.io.*;
import java.util.*;

public class Main {

    private static String[] input;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        if (str.contains("::")) {
            str = str.replace("::", ":group:");
        }

        input = str.split(":");
    }

    private static void solveProblem() {
        List<String> ans = new ArrayList<>();

        for (String inputStr : input) {
            if (inputStr.equals("group")) {
                for (int i = 0; i < 8 - input.length + 1; i++) {
                    ans.add("0000");
                }
                continue;
            }

            ans.add("0".repeat(4 - inputStr.length()) + inputStr);
        }

        for (int i = 0; i < 8; i++) {
            System.out.print(ans.get(i));

            if (i != 7) {
                System.out.print(":");
            }
        }
    }
}
