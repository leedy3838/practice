package baekjoon_3003;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int[] numArr = {1, 1, 2, 2, 2, 8};

        for(int i = 0; i < 6; i++){
            numArr[i] = numArr[i] - Integer.parseInt(input[i]);
        }

        for(int i = 0; i < 6; i++)
            System.out.print(numArr[i] + " ");
        System.out.println();
    }
}
