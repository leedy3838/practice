package baekjoon_1159;

import java.io.*;

public class Main {

    private static final int[] arr = new int[26];

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            String name = br.readLine();
            int index = name.charAt(0) - 'a';
            arr[index]++;
        }
    }

    private static void solveProblem() {
        boolean isExist = false;

        for (int i = 0; i < 26; i++) {
            if (arr[i] >= 5) {
                System.out.print((char) (i + 'a'));
                isExist = true;
            }
        }

        if (!isExist) {
            System.out.println("PREDAJA");
        }
    }
}
