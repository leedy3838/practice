package baekjoon_25556;

import java.io.*;
import java.util.*;

public class Main {

    private static int[] data;

    public static void main(String[] args) throws IOException {
        firstSetting();
        solveProblem();
    }

    private static void firstSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        data = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void solveProblem() {
        int[] stack = new int[4];

        boolean isOk = true;

        for (int num : data) {
            int place = findPlace(stack, num);

            if (place == 4) {
                isOk = false;
                break;
            } else {
                stack[place] = num;
            }
        }

        if (isOk) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static int findPlace(int[] stack, int num) {
        int place = 4;
        int minGap = Integer.MAX_VALUE;

        for (int i = 0; i < stack.length; i++) {
            if (stack[i] < num) {
                if (num - stack[i] < minGap) {
                    minGap = num - stack[i];
                    place = i;
                }
            }
        }

        return place;
    }
}
